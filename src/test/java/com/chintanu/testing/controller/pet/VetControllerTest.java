package com.chintanu.testing.controller.pet;

import com.chintanu.testing.model.pet.Vet;
import com.chintanu.testing.model.pet.Vets;
import com.chintanu.testing.service.pet.ClinicService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @InjectMocks
    VetController controller;

    @Mock
    ClinicService clinicService;

    Collection<Vet> vets;

    @BeforeEach
    void setUp() {

        Vet vet = new Vet();
        vets = new ArrayList<>();
        vets.add(vet);
    }

    @Test
    void showVetList() {

        //given
        Map<String, Object> model = new HashMap<>();
        BDDMockito.given(clinicService.findVets()).willReturn(vets);
        //when
        String ret = controller.showVetList(model);
        //then
        BDDMockito.then(clinicService).should(BDDMockito.times(1)).findVets();
        Assertions.assertThat("vets/vetList").isEqualToIgnoringCase(ret);
        Assertions.assertThat(model).size().isGreaterThan(0);
        BDDMockito.then(clinicService).shouldHaveNoMoreInteractions();
    }

    @Test
    void showResourcesVetList() {

        //given
        BDDMockito.given(clinicService.findVets()).willReturn(vets);

        //when
        Vets ret = controller.showResourcesVetList();

        //then
        BDDMockito.then(clinicService).should(BDDMockito.times(1)).findVets();
        org.junit.jupiter.api.Assertions.assertNotNull(ret);
        Assertions.assertThat(ret.getVetList().size()).isGreaterThan(0);
        BDDMockito.then(clinicService).shouldHaveNoMoreInteractions();
    }
}