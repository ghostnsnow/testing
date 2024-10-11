package com.chintanu.testing.controller;

import com.chintanu.testing.domain.Person;
import com.chintanu.testing.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    PersonService service;

    @InjectMocks
    PersonController controller;

    @Test
    void testProcessCreationFormError() {

        //given
        BindingResult bindingResult = BDDMockito.mock(BindingResult.class);
        Person person = Person.builder()
                .id(3)
                .age(45)
                .name("John")
                .build();
        BDDMockito.given(bindingResult.hasErrors()).willReturn(true);

        //when
        String ret = controller.processCreationForm(person,bindingResult);

        //then
        Assertions.assertThat(ret).isEqualToIgnoringCase("error");
    }

    @Test
    void testProcessCreationFormSuccess() {

        //given
        BindingResult bindingResult = BDDMockito.mock(BindingResult.class);
        Person person = Person.builder()
                .id(3)
                .age(45)
                .name("John")
                .build();

        Person callingPerson = Person.builder()
                .id(5)
                .age(67)
                .name("Doe")
                .build();

        BDDMockito.given(bindingResult.hasErrors()).willReturn(false);
        BDDMockito.given(service.savePerson(BDDMockito.any(Person.class))).willReturn(person);

        //when
        String ret = controller.processCreationForm(callingPerson, bindingResult);

        //then
        Assertions.assertThat(ret).isEqualToIgnoringCase("redirect:/person/3");
        BDDMockito.then(service).should(BDDMockito.times(1)).savePerson(callingPerson);
    }
}