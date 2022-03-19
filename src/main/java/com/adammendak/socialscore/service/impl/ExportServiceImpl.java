package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.dao.repository.UrlRepository;
import com.adammendak.socialscore.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {

    private final UrlRepository urlRepository;

    @Override
    public void export() {
        urlRepository.findAll().forEach(System.out::println);
    }
}
