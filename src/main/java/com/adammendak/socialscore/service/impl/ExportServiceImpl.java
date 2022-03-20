package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.dao.model.DomainAggregatedUrls;
import com.adammendak.socialscore.dao.repository.UrlRepository;
import com.adammendak.socialscore.service.ExportService;
import com.adammendak.socialscore.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExportServiceImpl implements ExportService {

    private final UrlRepository urlRepository;

    String[] HEADERS = { "domain", "urls", "social_score"};

    @Override
    public void export() {
        try{
            String filename = createCSVFile(urlRepository.getAllAndAggregateByDomain());
            log.info("EXPORT FILE NAME: {}", filename);
        } catch (IOException e) {
            log.error("EXPORT FAILED !");
            e.printStackTrace();
        }
    }

    public String createCSVFile(List<DomainAggregatedUrls> urls) throws IOException {
        String fileName = getFileName();
        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (DomainAggregatedUrls url: urls) {
                printer.printRecord(url.getDomain(), url.getUrls(), url.getSocialScore());
            }
        }
        return fileName;
    }

    private String getFileName() {
        return "result_" + DateTimeUtils.getCreatedDateTime() + ".csv";
    }

}
