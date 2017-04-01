package com.spr.controller;

import com.spr.exception.AccountNotFound;
import com.spr.model.Account;
import com.spr.model.History;
import com.spr.service.AccountService;
import com.spr.service.HistoryService;
import com.spr.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Controller
@RequestMapping(value="/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private AccountValidator accountValidator;



    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newAccountPage() {

        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("account", new Account());
        model.put("history", new History());

        ModelAndView mav = new ModelAndView("account-add", "account", model);
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewAccount(@ModelAttribute @Valid HashMap<String, Object> model,
                                        BindingResult result,
                                        final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return new ModelAndView("account-add");

        ModelAndView mav = new ModelAndView();
        String message = "New account was successfully created.";
        Account account = ((Account)model.get("account"));
        String action = "New account created for " + account.getClientID();
        accountService.create(account);
        historyService.create((History)model.get("history"), new Integer(1), action);

        mav.setViewName("redirect:/index.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editAccountPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("account-edit");
        Account account = accountService.findById(id);
        mav.addObject("account", account);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editAccount(@ModelAttribute @Valid Account account,
                                   BindingResult result,
                                   @PathVariable Integer id,
                                   final RedirectAttributes redirectAttributes) throws AccountNotFound {

        if (result.hasErrors())
            return new ModelAndView("account-edit");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Account was successfully updated.";
        accountService.update(account);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView clientListPage() {
        ModelAndView mav = new ModelAndView("account-list");
        List<Account> accountList = accountService.findAccounts();
        mav.addObject("accountList", accountList);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable Integer id,
                                      final RedirectAttributes redirectAttributes) throws AccountNotFound {

        ModelAndView mav = new ModelAndView("redirect:/index.html");

        Account account = accountService.delete(id);
        String message = "The account with : "+ account.getId() +" was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showAccountPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("account-detail");
        Account account = accountService.findById(id);
        mav.addObject("account", account);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public ModelAndView showAccount(@ModelAttribute @Valid Account account,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws AccountNotFound{

        if (result.hasErrors())
            return new ModelAndView("account-detail");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Account was successfully updated.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
}