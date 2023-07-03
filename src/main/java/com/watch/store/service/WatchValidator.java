package com.watch.store.service;

import com.watch.store.dto.WatchDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class WatchValidator {

    public void validate(WatchDto watchDto, BindingResult bindingResult){
        if (watchDto.getTitle().isEmpty()){
            FieldError fieldError = new FieldError(
                    "watchDto","quantity","Please fill mandatory fields!");
            bindingResult.addError(fieldError);
        }
    }
}
