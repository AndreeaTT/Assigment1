package com.spr.controller;

import java.util.List;

import javax.validation.Valid;

import com.spr.model.History;
import com.spr.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spr.exception.ClientNotFound;
import com.spr.model.Client;
import com.spr.service.ClientService;
import com.spr.validation.ClientValidator;


@Controller
@RequestMapping(value="/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ClientValidator clientValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(clientValidator);
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView newClientPage() {
        ModelAndView mav = new ModelAndView("client-add", "client", new Client());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewClient(@ModelAttribute @Valid Client client,
                                      BindingResult result,
                                      final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return new ModelAndView("client-add");

        ModelAndView mav = new ModelAndView();
        String message = "New client:  "+client.getName()+" was successfully created.";
        clientService.create(client);

        String action = "Create new client with ID:";
        historyService.createClient(client.getId(), client.getId(), action);

        mav.setViewName("redirect:/client-detail.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editClientPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("client-edit");
        Client client = clientService.findById(id);

        String action = "Edit client with ID:";
        historyService.createClient(client.getId(), client.getId(), action);

        mav.addObject("client", client);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editClient(@ModelAttribute @Valid Client client,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws ClientNotFound {

        if (result.hasErrors())
            return new ModelAndView("client-edit");

        ModelAndView mav = new ModelAndView("redirect:/client-detail.html");
        String message = "Client was successfully updated.";
        clientService.update(client);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView clientListPage() {
        ModelAndView mav = new ModelAndView("client-list");
        List<Client> clientList = clientService.findAllClients();
        mav.addObject("clientList", clientList);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showClientPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("client-detail");
        Client client = clientService.findById(id);
        mav.addObject("client", client);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public ModelAndView showClient(@ModelAttribute @Valid Client client,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws ClientNotFound{

        if (result.hasErrors())
            return new ModelAndView("client-detail");

        ModelAndView mav = new ModelAndView("redirect:/client-detail.html");
        String message = "Client was successfully found.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
}