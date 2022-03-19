package com.adammendak.socialscore.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandDTO {

    private String command;
    private String url;
    private String socialScore;

}
