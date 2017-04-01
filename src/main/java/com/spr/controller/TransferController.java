package com.spr.controller;

import com.spr.exception.AccountNotFound;
import com.spr.model.Transfer;
import com.spr.service.AccountService;
import com.spr.validation.AccountValidator;
import com.spr.validation.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Andreea ADM on 3/29/2017.
 */

@Controller
@RequestMapping(value="/transfer")
public class TransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransferValidator transferValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(transferValidator);
    }

    @RequestMapping(value="/money", method= RequestMethod.GET)
    public ModelAndView newTransferPage() {
        ModelAndView mav = new ModelAndView("account-transfer", "transfer", new Transfer());
        return mav;
    }

    @RequestMapping(value="/money", method=RequestMethod.POST)
    public ModelAndView createNewAccount(@ModelAttribute @Valid Transfer transfer,
                                         BindingResult result,
                                         final RedirectAttributes redirectAttributes) throws AccountNotFound{

        if (result.hasErrors())
            return new ModelAndView("account-transfer");

        ModelAndView mav = new ModelAndView("redirect:/index.html");

        accountService.updateAmount(transfer.getSenderID(), transfer.getValue());
        accountService.updateAmount(transfer.getReceiverID(), -transfer.getValue());

        String message = "New account was successfully created.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
}
