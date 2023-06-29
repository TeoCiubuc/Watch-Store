package com.watch.store.controller;

import com.watch.store.entity.Watch;
import com.watch.store.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class WatchController {
@Autowired
    WatchService watchService;
    @GetMapping("/")
    public String viewTemplate(Model model) {
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

        if(authenticated){
            List<Watch> watches = watchService.listAllWatches();
            model.addAttribute("watches",watches );
//            ChosenItemDto chosenItemDto = new ChosenItemDto();
//            model.addAttribute("chosenItemDto", chosenItemDto);
            return "watches";
        }else{
            return "login";
        }
    }
    @GetMapping("/watches")
    public String viewAllWatches(Model model){
        List<Watch> watchList = watchService.listAllWatches();
        model.addAttribute("watches",watchList);
        return "watches";
    }
}
