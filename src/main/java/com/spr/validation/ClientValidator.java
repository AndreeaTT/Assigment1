package com.spr.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.spr.model.Client;

@Component
public class ClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {

        return Client.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "client.name.empty");
        if (client.getCardNumber().length() != 16)
            errors.rejectValue("cardNumber", "client.cardNumber.length");
        if (client.getNumericCode().length() != 13)
            errors.rejectValue("numericCode", "client.numericCode.length");
    }
}