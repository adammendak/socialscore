package com.adammendak.socialscore.dao.repository;

import com.adammendak.socialscore.dao.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {

}
