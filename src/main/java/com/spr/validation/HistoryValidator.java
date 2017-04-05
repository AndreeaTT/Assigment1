package com.spr.validation;

import com.spr.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import org.springframework.validation.Validator;
import com.spr.service.HistoryService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Andreea ADM on 4/4/2017.
 */

@Component
public class HistoryValidator implements Validator{

    @Autowired
    private HistoryService historyService;

    @Override
    public boolean supports(Class<?> aClass) {

        return History.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors){
        History history = (History) target;
        boolean matchFormat = false;

        ValidationUtils.rejectIfEmpty(errors, "actionData", "history.data.empty");

        if (history.getActionData() != null) {
            matchFormat = history.getActionData().matches("[0-3]*[0-9]\\-[0-1]*[0-9]\\-[0-9][0-9][0-9][0-9]");
            if (!matchFormat)
                errors.rejectValue("actionData", "history.data.invalid");
        }

        if (matchFormat)
            if (history.getActionData() != null && history.getActionData().indexOf("-") != -1 && history.getActionData().indexOf("-", history.getActionData().indexOf("-") + 1) != -1) {
                int first = history.getActionData().indexOf("-");
                int second = history.getActionData().indexOf("-", first + 1);
                int day = Integer.parseInt(history.getActionData().substring(0, first));
                int month = Integer.parseInt(history.getActionData().substring(first + 1, second));

                try {
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date date = formatDate.parse(history.getActionData());
                    java.util.Date curentDate = new java.util.Date();
                    if (date.compareTo(curentDate) > 0)
                        errors.rejectValue("actionData", "history.data.future");
                }
                catch (ParseException e){

                }

                if (month > 12 || month < 1)
                     errors.rejectValue("actionData", "history.data.month");

                switch(month){
                    case 1:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 2:{
                        if (day >28 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 3:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 4:{
                        if (day >30 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 5:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 6:{
                        if (day >30 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 7:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 8:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 9:{
                        if (day >30 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 10:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 11:{
                        if (day >30 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                    case 12:{
                        if (day >31 || day < 1)
                            errors.rejectValue("actionData", "history.data.day");
                        break;
                    }
                }
            }
    }
}
