package com.spr.controller;

import com.spr.exception.AccountNotFound;
import com.spr.exception.BillNotFound;
import com.spr.model.Account;
import com.spr.service.ClientService;
import com.spr.service.AccountService;
import com.spr.service.BillService;
import com.spr.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import com.spr.model.Bill;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Andreea ADM on 4/3/2017.
 */

@Controller
@RequestMapping(value="/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private HistoryService historyService;

    private Bill balance;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView billListPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("bill-list");
        List<Bill> billList = billService.findAll();
        mav.addObject("billList", billList);
        return mav;
    }

    @RequestMapping(value="/select/{id}", method=RequestMethod.GET)
    public ModelAndView selectAccountPage(@PathVariable Integer id,
                                          final RedirectAttributes redirectAttributes){
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("bill-accounts");
        Bill bill = billService.findById(id);
        List<Account> accountList = accountService.findByBalanceValue(bill.getClientID(), bill.getValue());
        mav.addObject("accountList", accountList);
        if (accountList.isEmpty()){
            String message = "Don't found account for pay the bill.";
            redirectAttributes.addFlashAttribute("message", message);
            return new ModelAndView("redirect:/bill/list.html");
        }

        balance = bill;
        return mav;
    }

    @RequestMapping(value="/pay/{id}", method=RequestMethod.GET)
    public ModelAndView payBillPage(@PathVariable Integer id)throws AccountNotFound, BillNotFound{
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("redirect:/bill/list.html");
        Account account = accountService.findById(id);
        account.setAmount(account.getAmount() - balance.getValue());
        billService.delete(balance.getId());
        accountService.update(account);

        String action = "Pay bill of value ";
        historyService.createAccount(LoginController.loggedUser, balance.toString(), account.getClientID(), action);
        return mav;
    }
}
