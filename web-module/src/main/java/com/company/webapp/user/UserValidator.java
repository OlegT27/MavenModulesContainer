package com.company.webapp.user;

import com.company.webapp.util.WebComponent;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@WebComponent
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        // check if empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.emptylabel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sname", "error.emptylabel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patr", "error.emptylabel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bdate", "error.emptylabel");
    }
}
