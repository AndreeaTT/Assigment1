package com.spr.controller;

import org.springframework.web.bind.WebDataBinder;
import com.spr.model.History;
import com.spr.service.HistoryService;
import com.spr.validation.HistoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

import com.spr.exception.HistoryNotFound;
import java.util.List;

/**
 * Created by Andreea ADM on 3/29/2017.
 */

@Controller
@RequestMapping(value="/history")
public class HistoryController{

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryValidator historyValidator;

    private Integer historyId;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(historyValidator);
    }

    @RequestMapping(value="/list/{id}", method= RequestMethod.GET)
    public ModelAndView historyListPage(@PathVariable Integer id) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("history-list-all");
        List<History> historyList = historyService.findByUser(id);
        historyId = id;
        mav.addObject("historyList", historyList);
        return mav;
    }

    @RequestMapping(value="/list/history", method= RequestMethod.GET)
    public ModelAndView historyListPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("history-list-all");
        List<History> historyList = historyService.findHistory();
        mav.addObject("historyList", historyList);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showHistoryPage(@PathVariable Integer id) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("history-detail");
        History history = historyService.findById(id);
        mav.addObject("history", history);
        return mav;
    }

    @RequestMapping(value="/history", method= RequestMethod.GET)
    public ModelAndView newReportPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("history-display", "history", new History());
        return mav;
    }

    @RequestMapping(value="/history", method=RequestMethod.POST)
    public ModelAndView showReportPage(@ModelAttribute @Valid History history,
                                         BindingResult result,
                                         final RedirectAttributes redirectAttributes) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors())
            return new ModelAndView("history-display");

        ModelAndView mav = new ModelAndView("history-list-all");
        List<History>  historyList= historyService.getReportForPeriod(history.getActionData(),historyId);
        mav.addObject("historyList", historyList);
        return mav;
    }
}
