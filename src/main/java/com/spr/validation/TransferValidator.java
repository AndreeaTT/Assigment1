package com.spr.validation;

import com.spr.model.Transfer;
import com.spr.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by Andreea ADM on 3/30/2017.
 */

@Component
public class TransferValidator implements Validator{

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Transfer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transfer transfer = (Transfer)target;

        ValidationUtils.rejectIfEmpty(errors, "senderID", "transfer.sender.empty");
        ValidationUtils.rejectIfEmpty(errors, "receiverID", "transfer.receiver.empty");
        ValidationUtils.rejectIfEmpty(errors, "value", "transfer.value.empty");

        if (accountService.findById(transfer.getSenderID()) == null)
            errors.rejectValue("senderID", "transfer.sender.invalid");
        if (accountService.findById(transfer.getReceiverID()) == null)
            errors.rejectValue("receiverID", "transfer.receiver.invalid");

        if (transfer.getValue() < 0.0)
            errors.rejectValue("value", "transfer.value.negative");
        if (transfer.getValue() > accountService.findById(transfer.getSenderID()).getAmount())
            errors.rejectValue("value","transfer.value.toomuch");
    }
}
