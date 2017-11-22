package com.company.webapp.util;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResponse {
    private String status;
    private Map<String, String> errorMessages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getErrorMessages() {
        return this.errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessageList) {
        this.errorMessages = errorMessageList;
    }

    public void setErrorMessageList(List<FieldError> fieldErrors) {
        this.errorMessages = new HashMap<>();
        for (FieldError field : fieldErrors) {
            this.errorMessages.put(field.getField(), field.getDefaultMessage());
        }
    }

}

