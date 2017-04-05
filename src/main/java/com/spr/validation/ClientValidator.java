package com.spr.validation;

import com.spr.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.spr.model.Client;

@Component
public class ClientValidator implements Validator {

    @Autowired
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {

        return Client.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "client.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "address", "client.address.empty");

        if (client.getCardNumber() != null)
        if (client.getCardNumber().length() != 16)
            errors.rejectValue("cardNumber", "client.cardNumber.length");

        if (client.getNumericCode() != null)
        if (client.getNumericCode().length() != 13)
            errors.rejectValue("numericCode", "client.numericCode.length");

        if (client.getCardNumber() != null)
        if (!(client.getCardNumber().matches("[0-9]+")))
            errors.rejectValue("cardNumber", "client.cardNumber.invalid");

        if (client.getNumericCode() != null)
        if (!(client.getNumericCode().matches("[0-9]+")))
            errors.rejectValue("numericCode", "client.numericCode.invalid");

        if (client.getName() != null)
        if (!(client.getName().matches("[a-zA-Z]+")))
            errors.rejectValue("name", "client.name.invalid");

        if (client.getNumericCode() != null)
            if (clientService.findClientByNumericCode(client.getNumericCode()) != null )
                if (clientService.findClientByNumericCode(client.getNumericCode()).getId() != client.getId())
                    errors.rejectValue("numericCode", "client.numericCode.duplicate");
    }
}