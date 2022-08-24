package com.blaze.demo.service;

import com.blaze.demo.domain.model.company.Company;
import com.blaze.demo.rest.requests.CompanyRegisterRequest;

public interface CompanyService {
    Company registerCompany (CompanyRegisterRequest companyRegisterRequest);
}
