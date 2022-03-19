package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.dao.model.Url;
import com.adammendak.socialscore.dao.repository.UrlRepository;
import com.adammendak.socialscore.service.UrlService;
import com.adammendak.socialscore.service.dto.CommandDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public void addUrl(CommandDTO dto) {
        try {
            urlRepository.save(new Url(dto.getUrl(),
                    getDomainFromUrl(dto.getUrl()),
                    Integer.valueOf(dto.getSocialScore())));
            log.info("URL SAVED: {}", dto.getUrl());
        } catch (MalformedURLException e) {
            log.error("URL MALFORMED: {}", dto.getUrl());
        }
    }

    @Override
    public void removeUrl(String url) {
        urlRepository.findById(url)
            .ifPresentOrElse(
                (x) -> {
                    urlRepository.delete(x);
                    log.info("URL DELETED: {}", url);
                },
                () -> log.error("URL NOT FOUND: {}", url)
            );
    }

    private String getDomainFromUrl(String url) throws MalformedURLException {
        return new URL(url).getHost().replace("www.", "");
    }

}
