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

        if (account.getAmount() < 0.0)
            errors.rejectValue("amount", "account.amount.value");

        if (clientService.findById(account.getClientID()) == null)
            errors.rejectValue("clientID", "account.client.invalid");

    }
}