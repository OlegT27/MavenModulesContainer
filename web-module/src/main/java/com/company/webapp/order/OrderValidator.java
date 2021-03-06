package com.company.webapp.order;

import com.company.webapp.util.WebComponent;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@WebComponent
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotBlank.orderToAdd.name");
    }
}
