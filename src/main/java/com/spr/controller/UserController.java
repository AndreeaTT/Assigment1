package com.spr.controller;

import com.spr.exception.UserNotFound;
import com.spr.model.User;
import com.spr.service.HistoryService;
import com.spr.service.UserService;
import com.spr.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.*;
import com.spr.model.History;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newUserPage() {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("user", new User());
        model.put("history", new History());

        ModelAndView mav = new ModelAndView("user-add", "user", model);
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute @Valid HashMap<String, Object> model,
                                      BindingResult result,
                                      final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return new ModelAndView("user-add", populateDefaultModel());
        }

        User user = ((User)model.get("user"));
        String message = "New user was successfully created.";
        String action = "New account created for " + user.getId();

        userService.create(user);
        historyService.create((History)model.get("history"), new Integer(1), action);
        ModelAndView mav = new ModelAndView("redirect:/index.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("user-edit");
        User user = userService.findById(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute @Valid User user,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws UserNotFound{

        if (result.hasErrors())
            return new ModelAndView("user-edit", populateDefaultModel());

        ModelAndView mav = new ModelAndView("redirect:/index.html", populateDefaultModel());
        String message = "User was successfully updated.";

        userService.update(user);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView userListPage() {
        ModelAndView mav = new ModelAndView("user-list");
        List<User> userList = userService.findAllUsers();
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id,
                                   final RedirectAttributes redirectAttributes) throws UserNotFound {

        ModelAndView mav = new ModelAndView("redirect:/index.html");

        User account =userService.delete(id);
        String message = "The user with : "+ account.getId() +" was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showUserPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("user-detail");
        User user = userService.findById(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public ModelAndView showUser(@ModelAttribute @Valid User user,
                                 BindingResult result,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws UserNotFound{

        if (result.hasErrors())
            return new ModelAndView("user-detail");

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "User was successfully updated.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    private Map<String, Object> populateDefaultModel() {
        Map<String, Object> model = new HashMap<String, Object>();

        List<String> rights = new ArrayList<String>();
        rights.add("Employee");
        rights.add("Admin");
        model.put("role", rights);

        return model;
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }

    @RequestMapping(value="/error-login", method=RequestMethod.GET)
    public ModelAndView invalidLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", true);
        return modelAndView;
    }

    @RequestMapping(value="/success-login", method=RequestMethod.GET)
    public ModelAndView successLogin() {
        return new ModelAndView("home");
    }
}
