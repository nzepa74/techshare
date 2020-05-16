
CompanyRegistrationDao.getLastCompanyId = SELECT C.companyId FROM tbl_company_setup C ORDER BY C.companyId DESC LIMIT 1
CompanyRegistrationDao.getCompanyList = SELECT C.companyId, C.companyName, C.description, C.status FROM tbl_company_setup C
CompanyRegistrationDao.deleteCompany = DELETE FROM tbl_company_setup WHERE companyId =:companyId
