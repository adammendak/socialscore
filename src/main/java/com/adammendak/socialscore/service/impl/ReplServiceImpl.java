package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.service.ReplService;
import org.springframework.stereotype.Service;

@Service
public class ReplServiceImpl implements ReplService {

    @Override
    public void run() {
        System.out.println("running");
    }

}
