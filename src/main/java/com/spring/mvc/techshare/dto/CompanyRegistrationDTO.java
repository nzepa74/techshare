package com.spring.mvc.techshare.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by nzepa on 4/20/2020.
 */
public class CompanyRegistrationDTO {
    //region private variables
    private Integer companyId;

    @NotNull(message = "Company name is required")
    @Size(min = 1, max = 150, message = "Company name  should be maximum of 255 characters")
    private String companyName;

    private String description;

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
