package com.spr.controller;

import com.spr.exception.TransferNotFound;
import com.spr.model.Transfer;
import com.spr.service.HistoryService;
import com.spr.service.TransferService;
import com.spr.validation.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(transferValidator);
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newTransferPage() {
        ModelAndView mav = new ModelAndView("account-transfer", "transfer", new Transfer());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewTranfer(@ModelAttribute @Valid Transfer transfer,
                                         BindingResult result,
                                         final RedirectAttributes redirectAttributes) throws TransferNotFound{

        if (result.hasErrors())
            return new ModelAndView("account-transfer");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        transferService.transferMoney(transfer);

        String action = "Transfer from account with ID";
        historyService.createAccount(transfer.getId(), transfer.getSenderID(), transfer.getReceiverID(), action);

        String message = "New transfer was successfully created.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView transferListPage() {
        ModelAndView mav = new ModelAndView("transfer-list");
        List<Transfer> transferList = transferService.findAll();
        mav.addObject("transferList", transferList);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public ModelAndView showTransfer(@ModelAttribute @Valid Transfer transfer,
                                    BindingResult result,
                                    @PathVariable Integer id,
                                    final RedirectAttributes redirectAttributes) throws TransferNotFound{

        if (result.hasErrors())
            return new ModelAndView("transfer-detail");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "Transfer was successfully found.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
}
