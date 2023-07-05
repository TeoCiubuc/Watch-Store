package com.watch.store.controller;

import com.watch.store.dto.ChosenWatchDto;
import com.watch.store.dto.ShoppingCartDto;
import com.watch.store.dto.UserDetailsDto;
import com.watch.store.dto.WatchDto;
import com.watch.store.entity.ChosenWatch;
import com.watch.store.entity.Watch;
import com.watch.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller

public class WatchController {
    @Autowired
    private WatchService watchService;
    @Autowired
    private WatchValidator watchValidator;
    @Autowired
    private ChosenWatchService chosenWatchService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/")
    public String viewTemplate(Model model) {
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

        if (authenticated) {
            List<Watch> watches = watchService.listAllWatches();
            model.addAttribute("watches", watches);
            ChosenWatchDto chosenWatchDto = ChosenWatchDto.builder().build();
            model.addAttribute("chosenWatchDto", chosenWatchDto);
            return "watches";
        } else {
            return "login";
        }
    }

    @GetMapping("/watches")
    public String viewAllWatches(Model model) {
        List<Watch> watchList = watchService.listAllWatches();
        model.addAttribute("watches", watchList);
        ChosenWatchDto chosenWatchDto = ChosenWatchDto.builder().build();
        model.addAttribute("chosenWatchDto", chosenWatchDto);
        return "watches";
    }

    @GetMapping("/watch")
    public String viewWatchForm(Model model) {
        WatchDto watchDto = new WatchDto();
        model.addAttribute("watch", watchDto);
        return "watch";
    }

    @PostMapping("/watch/save")
    public String saveWatch
            (@ModelAttribute("watch") WatchDto watchDto,
             BindingResult bindingResult,
             Model model,
             @RequestParam("coverImage") MultipartFile file) throws IOException {
        watchValidator.validate(watchDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("watch", watchDto);
            return "watch";
        }
        watchService.saveWatch(watchDto, file);
        List<Watch> list = watchService.listAllWatches();
        model.addAttribute("watches", list);
        ChosenWatchDto chosenWatchDto = ChosenWatchDto.builder().build();
        model.addAttribute("chosenWatchDto", chosenWatchDto);
        return "redirect:/watches";
    }

    @PostMapping("/watch/{watchId}")
    public String addToShoppingList(@PathVariable(value = "watchId") String watchId,
                                    @ModelAttribute ChosenWatchDto watchDto,
                                    BindingResult bindingResult,
                                    Model model) {
        model.addAttribute("chosenWatchDto", watchDto);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        chosenWatchService.addToCart(watchDto, watchId, email);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartByUserEmail(email);
        model.addAttribute("shoppingCartDto", shoppingCartDto);
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartByUserEmail(email);
        model.addAttribute("shoppingCartDto", shoppingCartDto);
        UserDetailsDto userDetailsDto = userService.getUserDetailsDto(email);
        model.addAttribute("userDetailsDto", userDetailsDto);
        return "checkout";
    }

    @PostMapping("/sendOrder")
    public String sendOrder(@ModelAttribute("UserDetailsDto") UserDetailsDto userDetailsDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        customerOrderService.addCustomerOrder(email, userDetailsDto.getShippingAddress());
        return "confirmation";
    }
}
