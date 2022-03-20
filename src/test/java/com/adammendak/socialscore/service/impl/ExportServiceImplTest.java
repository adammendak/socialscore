package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.dao.model.DomainAggregatedUrls;
import com.adammendak.socialscore.dao.repository.UrlRepository;
import com.adammendak.socialscore.util.DateTimeUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExportServiceImplTest {

    private final String[] HEADERS = { "domain", "urls", "social_score"};
    private final Map<String, Pair<String, String>> RECORDS_MAP = Map.of("test.com", Pair.of("5", "30"),
            "prod.com", Pair.of("10", "50"));

    @InjectMocks
    private ExportServiceImpl exportService;

    @Mock
    private UrlRepository urlRepository;

    @Test
    public void export_correct_done() throws IOException {
        //given
        List<DomainAggregatedUrls> mockedList = List.of(
                getDomainAggregatedUrls("test.com", 5, BigInteger.valueOf(30L)),
                getDomainAggregatedUrls("prod.com", 10, BigInteger.valueOf(50L)));
        when(urlRepository.getAllAndAggregateByDomain()).thenReturn(mockedList);

        //when
        exportService.export();

        //then
        File rootFolder = new File(new File("").getAbsolutePath());
        File[] matchingFiles = rootFolder.listFiles((dir, name) -> isCreatedWithin5Seconds(name));
        Reader in = new FileReader(matchingFiles[0].getName());

        getRecords(in).forEach(x -> {
            String domain = x.get("domain");
            String urls = x.get("urls");
            String socialScore = x.get("social_score");
            assertThat(RECORDS_MAP).containsKey(domain);
            assertThat(RECORDS_MAP.get(domain)).isEqualTo(Pair.of(urls, socialScore));
        });

        matchingFiles[0].delete();
    }

    private boolean isCreatedWithin5Seconds(String name) {
        if(name.contains(".csv")) {
            String[] parsed = name.split("_");
            String time = parsed[2].replace(".csv", "");
            return DateTimeUtils.checkIfCreatedTimeWithin5Seconds(time);
        }
        return false;
    }

    private CSVParser getRecords(Reader in) throws IOException {
        return CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withDelimiter(';')
                .withFirstRecordAsHeader()
                .parse(in);
    }

    private DomainAggregatedUrls getDomainAggregatedUrls(String domain, Integer urls, BigInteger score) {
        return new DomainAggregatedUrls() {
            @Override
            public String getDomain() {
                return domain;
            }

            @Override
            public Integer getUrls() {
                return urls;
            }

            @Override
            public BigInteger getSocialScore() {
                return score;
            }
        };
    }

}