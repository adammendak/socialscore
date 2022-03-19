package com.adammendak.socialscore.service;

import com.adammendak.socialscore.service.dto.CommandDTO;

public interface UrlService {

    void addUrl(CommandDTO dto);

    void removeUrl(String url);

}
