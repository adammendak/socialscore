package com.adammendak.socialscore.dao.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "URL")
@Data
public class Url {

    @Id
    @Column(name = "URL", updatable = false, nullable = false)
    private String url;

    @Column(name = "DOMAIN", updatable = false, nullable = false)
    private String domain;

    @Column(name = "SOCIAL_SCORE", nullable = false)
    private Integer socialScore;

}
