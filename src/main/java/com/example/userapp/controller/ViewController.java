package com.example.userapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ViewController {
    private UserController userController;

    @RequestMapping("/view/users")
    public ModelAndView allUsers(@RequestParam(defaultValue = "5") Integer count,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        modelAndView.addObject("users", userController.getAll(count, page, sortBy));
        return modelAndView;
    }

    @RequestMapping("/view/users/{id}")
    public ModelAndView User(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", userController.getById(id));
        return modelAndView;
    }
}
