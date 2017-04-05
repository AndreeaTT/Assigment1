package com.spr.validation;

import com.spr.model.Account;
import com.spr.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Component
public class AccountValidator implements Validator {

    @Autowired
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {

        return Account.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors){
        Account account = (Account) target;

        ValidationUtils.rejectIfEmpty(errors, "clientID", "account.client.empty");
        ValidationUtils.rejectIfEmpty(errors, "amount", "account.amount.empty");
        ValidationUtils.rejectIfEmpty(errors, "iban", "account.iban.empty");

        if (account.getAmount() != null)
        if (Double.compare(account.getAmount() , new Double(0.0) )< 0)
            errors.rejectValue("amount", "account.amount.value");

        if (account.getClientID() != null)
            if (clientService.findById(account.getClientID()) == null)
                 errors.rejectValue("clientID", "account.client.invalid");

        if (!(account.getClientID() instanceof Integer))
            errors.rejectValue("clientID", "account.client.integer");

        if (!(account.getAmount() instanceof Double))
            errors.rejectValue("clientID", "account.amount.double");

        if (account.getIban() != null)
            if (!(account.getIban().matches("RO[0-9]+")))
                errors.rejectValue("iban", "account.iban.invalid");

        if (account.getIban() != null)
            if (account.getIban().length() != 24)
                errors.rejectValue("iban", "account.iban.length");

        if (account.getTypeAccount().equals("None"))
            errors.rejectValue("typeAccount", "account.type.select");
    }
}
