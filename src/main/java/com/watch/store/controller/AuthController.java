package com.watch.store.controller;

import com.watch.store.dto.UserDto;
import com.watch.store.dto.UserUpdateDto;
import com.watch.store.service.UserService;
import com.watch.store.service.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private UserService userService;
    private UserValidator userValidator;
    @Autowired
    public AuthController(UserService userService,UserValidator userValidator) {

        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user")UserDto userDto, BindingResult bindingResult, Model model){
        System.out.println("USER DIN POST USER " + userDto);
        userValidator.validate(userDto,bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("user",userDto);
            return "register";
        }
        userService.saveUser(userDto);
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String viewRegister(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "register";
    }

    @RequestMapping("/profile/save")
    public String saveProfile(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + userDto);
        //userValidator.validate(userDto, bindingResult);
        //if (bindingResult.hasErrors())
        //model.addAttribute("user", userDto);
        //return "profile";}
        userService.updateUser(userDto);
        return "user";}



    @PostMapping("/profile/save")
    public String updateUser( @ModelAttribute("user") UserUpdateDto userUpdateDto,
                              BindingResult result,
                              Model model) throws Exception {
        System.out.println("###########################################" + userUpdateDto);
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        //userValidator.validate(userUpdateDto, result);
        if (result.hasErrors()) {
            model.addAttribute("user", userUpdateDto);
            return "user";
        }
        userService.updateUser(userUpdateDto);
        return "redirect:/watches";
    }
}
