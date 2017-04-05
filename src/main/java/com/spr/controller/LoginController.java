package com.spr.controller;

import com.spr.exception.UserNotFound;
import com.spr.service.SecurityService;
import com.spr.validation.LoginValidator;
import com.spr.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spr.model.User;
import javax.validation.Valid;

/**
 * Created by Andreea ADM on 4/1/2017.
 */

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LoginValidator loginValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    protected static String loggedUser = "";
    protected static String role = "";

    @RequestMapping(value="index", method=RequestMethod.GET)
    public ModelAndView index() {
        if (loggedUser.isEmpty())
           return new ModelAndView("login");

        if (role.equals("Employee"))
             return new ModelAndView("header");
        else
            return new ModelAndView("header-admin");
    }

    @RequestMapping(value = { "/" ,"login"}, method = RequestMethod.GET)
    public ModelAndView loginPageShow() {
        ModelAndView mav;

        if (loggedUser.isEmpty()) {
            mav = new ModelAndView("login");
            mav.addObject("user", new User());
        }
        else {
            try{
                if (securityService.findRole(loggedUser).equals("Admin"))
                    mav = new ModelAndView("header-admin");
                else
                    mav = new ModelAndView("header");
            }
            catch (UserNotFound e){
                mav = new ModelAndView("login");
            }
        }
        return mav;
    }

    @RequestMapping(value = { "/" ,"login"}, method = RequestMethod.POST)
    public ModelAndView loginPage(@ModelAttribute @Valid User user,
                                  BindingResult result,
                                  final RedirectAttributes redirectAttributes) {
        ModelAndView mav = null;

        if (result.hasErrors())
            return new ModelAndView("login");

        if (loggedUser.isEmpty()) {
            try {
                String message = securityService.validLogin(user.getUsername(), user.getPassword());

                switch (message) {
                    case "LoginAdmin": {
                        mav = new ModelAndView("header-admin");
                        loggedUser = user.getUsername();
                        role = "Admin";
                        redirectAttributes.addFlashAttribute("loggedUser", user.getUsername());
                        break;
                    }
                    case "LoginEmployee": {
                        mav = new ModelAndView("header");
                        loggedUser = user.getUsername();
                        role = "Employee";
                        redirectAttributes.addFlashAttribute("loggedUser",user.getUsername());
                        break;
                    }
                    case "WrongPassword": {
                        mav = new ModelAndView("login");
                        redirectAttributes.addFlashAttribute("message", "Wrong password");
                        break;
                    }
                }
            }
            catch (UserNotFound e){
                mav = new ModelAndView("login");
                redirectAttributes.addFlashAttribute("message", "User not found");
            }
        }
        else{
            mav = new ModelAndView("index");
            redirectAttributes.addFlashAttribute("message", "You are already login.");
        }
        return mav;
    }

    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public ModelAndView logoutPage(){
            loggedUser = "";
            role = "";
            return new ModelAndView("login", "user", new User());
    }
}
