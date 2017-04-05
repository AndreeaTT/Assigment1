package com.spr.controller;

import com.spr.exception.AccountNotFound;
import com.spr.model.Account;
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
import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Controller
@RequestMapping(value="/account")
public class AccountController{

    @Autowired
    private AccountService accountService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newAccountPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("account-add", "account", new Account());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewAccount(@ModelAttribute @Valid Account account,
                                        BindingResult result,
                                        final RedirectAttributes redirectAttributes) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors())
            return new ModelAndView("account-add");

        String message = "New account was successfully created.";
        accountService.create(account);

        String action = "Create new account with IBAN:";
        historyService.createAccount(LoginController.loggedUser, account.getIban(), account.getClientID(), action);

        ModelAndView mav = new ModelAndView("redirect:/account/list.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editAccountPage(@PathVariable Integer id) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

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
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors())
            return new ModelAndView("account-edit");

        ModelAndView mav = new ModelAndView("redirect:/account/list.html");
        String message = "Account was successfully updated.";
        accountService.update(account);

        String action = "Edit account with IBAN:";
        historyService.createAccount(LoginController.loggedUser, account.getIban(), account.getClientID(), action);
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView clientListPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("account-list");
        List<Account> accountList = accountService.findAccounts();
        mav.addObject("accountList", accountList);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable Integer id,
                                      final RedirectAttributes redirectAttributes) throws AccountNotFound {

        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("redirect:/account/list.html");

        Account account = accountService.delete(id);
        String message = "The account with IBAN: "+ account.getIban() +" was successfully deleted.";

        String action = "Delete account with IBAN:";
        historyService.createAccount(LoginController.loggedUser, account.getIban(), account.getClientID(), action);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showAccountPage(@PathVariable Integer id) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

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
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors())
            return new ModelAndView("home");

        ModelAndView mav = new ModelAndView("account-detail");
        return mav;
    }
}
