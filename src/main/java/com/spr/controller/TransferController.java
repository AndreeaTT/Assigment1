package com.spr.controller;


import com.spr.exception.TransferNotFound;
import com.spr.model.Transfer;
import com.spr.service.AccountService;
import com.spr.service.HistoryService;
import com.spr.service.TransferService;
import com.spr.validation.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
    private TransferService transferService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TransferValidator transferValidator;

    @Autowired
    private AccountService accountService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(transferValidator);
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newTransferPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("transfer-create", "transfer", new Transfer());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewTranfer(@ModelAttribute @Valid Transfer transfer,
                                         BindingResult result,
                                         final RedirectAttributes redirectAttributes) throws TransferNotFound{
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Admin"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors())
            return new ModelAndView("transfer-create");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        transferService.transferMoney(transfer);

        String action = "Transfer from account with IBAN";
        historyService.createTransfer(LoginController.loggedUser,transfer.getSenderID(),transfer.getReceiverID(), action);

        String message = "Transfer was successfully made.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

}
