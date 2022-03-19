package com.adammendak.socialscore.dao.repository;

import com.adammendak.socialscore.dao.model.DomainAggregatedUrls;
import com.adammendak.socialscore.dao.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {

    @Query(value = " SELECT url.DOMAIN as domain, COUNT(url) as urls, SUM(url.SOCIAL_SCORE) as socialScore"
            + " FROM URL url GROUP BY url.DOMAIN", nativeQuery = true)
    List<DomainAggregatedUrls> getAllAndAggregateByDomain();

}
