package com.spring.mvc.techshare.service;

import com.spring.mvc.enumeration.SystemDataInt;
import com.spring.mvc.helper.BeanValidator;
import com.spring.mvc.helper.CurrentUser;
import com.spring.mvc.helper.ResponseMessage;
import com.spring.mvc.techshare.dao.CompanyRegistrationDao;
import com.spring.mvc.techshare.dto.CompanyRegistrationDTO;
import com.spring.mvc.techshare.entity.CompanyRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by nzepa on 4/20/2020.
 */
@Service
public class CompanyRegistrationService {
    //region private variables
    @Autowired
    protected BeanValidator beanValidator;
    @Autowired
    CompanyRegistrationDao companyRegistrationDao;
    //endregion

    //region public methods

    /**
     * save methods
     *
     * @param currentUser            -- CurrentUser
     * @param companyRegistrationDTO -- CompanyRegistrationDTO
     * @return -- ResponseMessage
     */
    public ResponseMessage saveCompanyRegistration(CurrentUser currentUser, CompanyRegistrationDTO companyRegistrationDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        beanValidator.Validate(companyRegistrationDTO, responseMessage);
        if (responseMessage.getResponseStatus() == SystemDataInt.MESSAGE_STATUS_UNSUCCESSFUL.value()) {
            return responseMessage;
        }
        if (companyRegistrationDTO.getCompanyId() == null) {
            Integer companyId = companyRegistrationDao.getLastCompanyId();
            companyId = companyId == null ? 0 : companyId;
            companyRegistrationDTO.setCompanyId(companyId + 1);
            CompanyRegistration companyRegistration = convertDtoToEntity(currentUser, companyRegistrationDTO);
            companyRegistrationDao.saveCompanyRegistration(companyRegistration);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseText("Data saved successfully");
        } else {
            CompanyRegistration companyRegistration = convertDtoToEntity(currentUser, companyRegistrationDTO);
            companyRegistrationDao.updateCompanyRegistration(companyRegistration);
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseText("Data updated successfully");
        }
        return responseMessage;
    }

    /**
     * to get company list
     *
     * @return -- ResponseMessage
     */
    public ResponseMessage getCompanyList() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<CompanyRegistrationDTO> companyRegistrationDTOs = companyRegistrationDao.getCompanyList();
        if (companyRegistrationDTOs != null) {
            responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
            responseMessage.setResponseDTO(companyRegistrationDTOs);
        }
        return responseMessage;
    }

    /**
     * delete method
     *
     * @param currentUser -- CurrentUser
     * @param companyId   -- Integer
     * @return -- ResponseMessage
     */
    public ResponseMessage deleteCompany(CurrentUser currentUser, Integer companyId) {
        ResponseMessage responseMessage = new ResponseMessage();
        //check reference before calling delete function
        // save to audit table
        companyRegistrationDao.deleteCompany(companyId);
        responseMessage.setResponseStatus(SystemDataInt.MESSAGE_STATUS_SUCCESSFUL.value());
        responseMessage.setResponseText("Deleted successfully");
        return responseMessage;
    }
    //endregion

    /**
     * private methods
     *
     * @param currentUser            -- CurrentUser
     * @param companyRegistrationDTO -- CompanyRegistrationDTO
     * @return -- CompanyRegistration
     */
    private CompanyRegistration convertDtoToEntity(CurrentUser currentUser, CompanyRegistrationDTO companyRegistrationDTO) {
        CompanyRegistration companyRegistration = new CompanyRegistration();
        companyRegistration.setCompanyId(companyRegistrationDTO.getCompanyId());
        companyRegistration.setCompanyName(companyRegistrationDTO.getCompanyName());
        companyRegistration.setDescription(companyRegistrationDTO.getDescription());
        companyRegistration.setStatus(companyRegistrationDTO.getStatus());
        companyRegistration.setCreatedBy("username");
        companyRegistration.setCreatedDate(new Date());
        return companyRegistration;
    }
}
