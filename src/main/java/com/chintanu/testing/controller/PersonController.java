package com.chintanu.testing.controller;

import com.chintanu.testing.domain.Person;
import com.chintanu.testing.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

@Controller
public class PersonController {

    @Autowired
    PersonService service;

    public String processCreationForm(@Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "error";
        } else {

            Person owner = service.savePerson(person);
            return "redirect:/person/" + owner.getId();
        }
    }
}
