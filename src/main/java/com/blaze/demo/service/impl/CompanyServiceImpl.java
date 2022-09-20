package com.blaze.demo.service.impl;

import com.blaze.demo.domain.model.company.Company;
import com.blaze.demo.rest.requests.CompanyRegisterRequest;
import com.blaze.demo.service.CompanyService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public Company registerCompany(CompanyRegisterRequest companyRegisterRequest) {
        return null;
    }
}
