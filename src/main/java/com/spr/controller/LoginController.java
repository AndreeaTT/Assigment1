package com.spr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.spr.exception.UserNotFound;
import com.spr.service.SecurityService;
import com.spr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import com.spr.model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

/**
 * Created by Andreea ADM on 4/1/2017.
 */

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public ModelAndView homePage(ModelMap model) {
        return new ModelAndView("home");
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }
}
