package com.company.webapp.service.dataproducer;

import com.company.webapp.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // check if empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.emptylabel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "error.emptylabel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patron", "error.emptylabel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "error.emptylabel");
    }
}
