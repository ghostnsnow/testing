package com.chintanu.testing.controller.pet;

import com.chintanu.testing.model.pet.Owner;
import com.chintanu.testing.service.pet.ClinicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

@SpringJUnitWebConfig
public class OwnerControllerTest {

    @Mock
    ClinicService clinicService;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;
    Collection<Owner> results;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        results = new ArrayList<>();
    }

    @Test
    public void initCreationForm() throws Exception {

        //given - done in setup

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())//then
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))//then
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"));//then

        //then
        BDDMockito.then(clinicService).shouldHaveNoInteractions();
    }

    @Test
    public void processFindFormEmptyResult() throws Exception {

        //given
        BDDMockito.given(clinicService.findOwnerByLastName(BDDMockito.anyString())).willReturn(results);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/owners").param("firstName", "John"))
                .andExpect(MockMvcResultMatchers.status().isOk())//then
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"));//then

        //then
        BDDMockito.then(clinicService).should(BDDMockito.times(1)).findOwnerByLastName(BDDMockito.anyString());
    }

    @Test
    public void processFindFormSingleResult() throws Exception {

        //given
        Owner owner = new Owner();
        owner.setId(1);
        results.add(owner);
        BDDMockito.given(clinicService.findOwnerByLastName(BDDMockito.anyString())).willReturn(results);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/owners").param("firstName", "John"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())//then
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        BDDMockito.then(clinicService).should(BDDMockito.times(1)).findOwnerByLastName(BDDMockito.anyString());
    }

    @Test
    public void processFindFormMultiResult() throws Exception {

        //given
        Owner owner = new Owner();
        owner.setId(1);
        Owner owner2 = new Owner();
        owner2.setId(2);

        results.add(owner);
        results.add(owner2);

        BDDMockito.given(clinicService.findOwnerByLastName(BDDMockito.anyString())).willReturn(results);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/owners").param("firstName", "John"))
                .andExpect(MockMvcResultMatchers.status().isOk())//then
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("selections"));

        BDDMockito.then(clinicService).should(BDDMockito.times(1)).findOwnerByLastName(BDDMockito.anyString());
    }

    @Test
    public void processUpdateOwnerFormValid() throws Exception {

        ArgumentCaptor<Owner> ownerArgumentCaptor = ArgumentCaptor.forClass(Owner.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{ownerId}/edit", 22)
                                                    .param("address",  "Thane")
                                                    .param("city", "Mumbai")
                                                    .param("telephone", "1234567890")
                                                    .param("firstName", "John")
                                                    .param("lastName", "Doe"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/{ownerId}"));

        BDDMockito.then(clinicService).should(BDDMockito.times(1)).saveOwner(ownerArgumentCaptor.capture());
        Owner captured = ownerArgumentCaptor.getValue();
        Assertions.assertEquals(22, captured.getId());
    }

    @Test
    public void processUpdateOwnerFormInValid() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{ownerId}/edit", 22)
                                                .param("address",  "Thane")
                                                //.param("city", "Mumbai")
                                                //.param("telephone", "1234567890")
                                                .param("firstName", "John")
                                                .param("lastName", "Doe"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("owner"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("owner", "city", "telephone"))
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"));

        BDDMockito.then(clinicService).shouldHaveNoInteractions();
    }
}