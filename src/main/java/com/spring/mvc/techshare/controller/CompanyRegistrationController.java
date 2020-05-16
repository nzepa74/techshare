package com.spring.mvc.techshare.controller;

import com.spring.mvc.helper.CurrentUser;
import com.spring.mvc.helper.ResponseMessage;
import com.spring.mvc.techshare.dto.CompanyRegistrationDTO;
import com.spring.mvc.techshare.service.CompanyRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nzepa on 4/20/2020.
 */

@Controller
@RequestMapping("/companyRegistration")
public class CompanyRegistrationController {

    //region private variables
    @Autowired
    private CompanyRegistrationService companyRegistrationService;
    //endregion

    ResponseMessage responseMessage;

    //region public methods

    /**
     * index method
     *
     * @return -- String
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "companyRegistration";
    }

    /**
     * save method
     *
     * @param request                -- HttpServletRequest
     * @param response               -- HttpServletResponse
     * @param companyRegistrationDTO -- CompanyRegistrationDTO
     * @return -- ResponseMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/saveCompanyRegistration", method = RequestMethod.POST)
    public ResponseMessage saveCompanyRegistration(HttpServletRequest request, HttpServletResponse response,
                                                   CompanyRegistrationDTO companyRegistrationDTO)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = companyRegistrationService.saveCompanyRegistration(currentUser, companyRegistrationDTO);
        return responseMessage;
    }

    /**
     * to get company list
     *
     * @return --- ResponseMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getCompanyList", method = RequestMethod.GET)
    public ResponseMessage getCompanyList() throws Exception {
        responseMessage = companyRegistrationService.getCompanyList();
        return responseMessage;
    }

    /**
     * delete method
     *
     * @param request   -- HttpServletRequest
     * @param response  -- HttpServletResponse
     * @param companyId -- Integer
     * @return -- ResponseMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    public ResponseMessage deleteCompany(HttpServletRequest request, HttpServletResponse response, Integer companyId)
            throws Exception {
        CurrentUser currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        responseMessage = companyRegistrationService.deleteCompany(currentUser, companyId);
        return responseMessage;
    }
    //endregion
}
