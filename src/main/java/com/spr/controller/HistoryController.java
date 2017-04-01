package com.spr.controller;

import com.spr.model.History;
import com.spr.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value="/list/{id}", method= RequestMethod.GET)
    public ModelAndView historyListPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("history-list-all");
        List<History> historyList = historyService.findByUser(id);
        mav.addObject("historyList", historyList);
        return mav;
    }

    @RequestMapping(value="/list/{id}", method=RequestMethod.POST)
    public ModelAndView historyPage(@ModelAttribute @Valid History history,
                                   BindingResult result,
                                   @PathVariable Integer id,
                                   final RedirectAttributes redirectAttributes) throws HistoryNotFound {

        if (result.hasErrors())
            return new ModelAndView("home");

        ModelAndView mav = new ModelAndView("history-list-all");
        List<History> historyList = historyService.findByUser(id);
        mav.addObject("historyList", historyList);
        return mav;
    }

    @RequestMapping(value="/list/history", method= RequestMethod.GET)
    public ModelAndView historyListPage() {
        ModelAndView mav = new ModelAndView("history-list-all");
        List<History> historyList = historyService.findHistory();
        mav.addObject("historyList", historyList);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showHistoryPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("history-detail");
        History history = historyService.findById(id);
        mav.addObject("history", history);
        return mav;
    }
}
