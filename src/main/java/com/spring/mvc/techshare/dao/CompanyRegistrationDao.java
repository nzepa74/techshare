package com.spring.mvc.techshare.dao;

import com.spring.mvc.helper.BaseDao;
import com.spring.mvc.techshare.dto.CompanyRegistrationDTO;
import com.spring.mvc.techshare.entity.CompanyRegistration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nzepa on 4/20/2020.
 */
@Repository
public class CompanyRegistrationDao extends BaseDao {

    @Transactional(readOnly = true)
    public Integer getLastCompanyId() {
        String sqlQuery = properties.getProperty("CompanyRegistrationDao.getLastCompanyId");
        org.hibernate.query.NativeQuery hQuery = (NativeQuery) hibernateQuery(sqlQuery);
        return (Integer) hQuery.uniqueResult();
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void saveCompanyRegistration(CompanyRegistration companyRegistration) {
        em.persist(companyRegistration);
    }


    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void updateCompanyRegistration(CompanyRegistration companyRegistration) {
        em.merge(companyRegistration);
    }

    @Transactional(readOnly = true)
    public List<CompanyRegistrationDTO> getCompanyList() {
        String sqlQuery = properties.getProperty("CompanyRegistrationDao.getCompanyList");
        org.hibernate.query.NativeQuery hQuery = (NativeQuery) hibernateQuery(sqlQuery, CompanyRegistrationDTO.class);
        return hQuery.list();
    }

    @Transactional(value = "txManager", rollbackFor = Exception.class)
    public void deleteCompany(Integer companyId) {
        String sqlQuery = properties.getProperty("CompanyRegistrationDao.deleteCompany");
        org.hibernate.query.NativeQuery hQuery = (NativeQuery) hibernateQuery(sqlQuery)
                .setParameter("companyId", companyId);
        hQuery.executeUpdate();
    }
}
