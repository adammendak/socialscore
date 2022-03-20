package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.dao.model.Url;
import com.adammendak.socialscore.dao.repository.UrlRepository;
import com.adammendak.socialscore.service.dto.CommandDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlServiceImplTest {

    public static final String TEST_URL = "http://www.test.com/1234";
    public static final String MALFORMED_URL = "httsdafasp://wasdfww.test.com/1234";

    @InjectMocks
    private UrlServiceImpl urlService;

    @Mock
    private UrlRepository urlRepository;

    @Test
    public void addUrl_correctInput_urlSaved() {
        //when
        urlService.addUrl(createCommandDTO("ADD", TEST_URL, "30"));

        //then
        verify(urlRepository, times(1)).save(getUrl(TEST_URL, "test.com", 30));
        verifyNoMoreInteractions(urlRepository);
    }

    @Test
    public void addUrl_malformedInput_errorThrown() {
        //when
        urlService.addUrl(createCommandDTO("ADD", MALFORMED_URL, "30"));

        //then
        verifyNoInteractions(urlRepository);
    }

    @Test
    public void removeUrl_found_urlDeleted() {
        //given
        when(urlRepository.findById(TEST_URL)).thenReturn(Optional.of(getUrl(TEST_URL, "test.com", 30)));

        //when
        urlService.removeUrl(TEST_URL);

        //then
        verify(urlRepository, times(1)).delete(getUrl(TEST_URL, "test.com", 30));
    }

    @Test
    public void removeUrl_notFound_noInteraction() {
        //given
        when(urlRepository.findById(TEST_URL)).thenReturn(Optional.empty());

        //when
        urlService.removeUrl(TEST_URL);

        //then
        verify(urlRepository, times(0)).delete(getUrl(TEST_URL, "test.com", 30));
    }

    private Url getUrl(String url, String domain, Integer socialScore) {
        return Url.builder()
                .url(url)
                .domain(domain)
                .socialScore(socialScore)
                .build();
    }

    private CommandDTO createCommandDTO(String command, String url, String score) {
        return CommandDTO.builder()
                .command(command)
                .url(url)
                .socialScore(score)
                .build();
    }
}