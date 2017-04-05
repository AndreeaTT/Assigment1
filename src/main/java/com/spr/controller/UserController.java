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
import org.springframework.ui.Model;

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

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newUserPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("user-add", "user", new User());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute @Valid User user,
                                      BindingResult result,
                                      final RedirectAttributes redirectAttributes) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors()) {
            return new ModelAndView("user-add");
        }

        String message = "New user was successfully created.";
        userService.create(user);
        ModelAndView mav = new ModelAndView("redirect:/user/list.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer id) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

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
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        if (result.hasErrors())
            return new ModelAndView("user-edit");

        String message = "User was successfully updated.";
        userService.update(user);

        ModelAndView mav = new ModelAndView("redirect:/user/list.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView userListPage() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("user-list");
        List<User> userList = userService.findByRight("Employee");
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id,
                                   final RedirectAttributes redirectAttributes) throws UserNotFound {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("redirect:/user/list.html");
        historyService.deleteAllEmployeeHistory(id);
        User user =userService.delete(id);
        String message = "The user with : "+ user.getId() +" was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView showUserPage(@PathVariable Integer id) {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");
        if (LoginController.role.equals("Employee"))
            return new ModelAndView("redirect:/index.html");

        ModelAndView mav = new ModelAndView("user-detail");
        User user = userService.findById(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value="/detail")
    public ModelAndView showUserDetail() {
        if (LoginController.loggedUser.isEmpty())
            return new ModelAndView("redirect:/login.html");

        User user = userService.findByUsername(LoginController.loggedUser);
        ModelAndView mav = new ModelAndView("redirect:/user/"+user.getId()+".html");
        mav.addObject("user", user);
        return mav;
    }
}

