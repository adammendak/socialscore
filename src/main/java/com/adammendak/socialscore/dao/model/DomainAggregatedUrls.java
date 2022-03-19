package com.adammendak.socialscore.dao.model;

import java.math.BigInteger;

public interface DomainAggregatedUrls {

    String getDomain();
    Integer getUrls();
    BigInteger getSocialScore();

}
