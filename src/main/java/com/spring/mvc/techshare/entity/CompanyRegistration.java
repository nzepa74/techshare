package com.spring.mvc.techshare.entity;

import com.spring.mvc.helper.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nzepa on 4/20/2020.
 */
@Entity
@Table(name = "tbl_company_setup")
public class CompanyRegistration extends BaseEntity {
    //region private variables
    @Id
    @Column(name = "companyId")
    private Integer companyId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Character status;
    //endregion

    //region setters and getters

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    //endregion
}
