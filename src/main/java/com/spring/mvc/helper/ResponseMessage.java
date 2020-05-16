package com.spring.mvc.helper;


public class ResponseMessage {
    //region private variables
    private Integer responseStatus;
    private String responseText;
    private Object responseDTO;

    //endregion
    public ResponseMessage() {
    }

    //region setters and getters

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Object getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(Object responseDTO) {
        this.responseDTO = responseDTO;
    }

    //endregion
}
