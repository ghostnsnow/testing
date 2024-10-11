package com.chintanu.testing.service.pet;

import com.chintanu.testing.model.pet.PetType;
import com.chintanu.testing.repository.pet.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    ClinicServiceImpl service;

    List<PetType> petTypes;

    @BeforeEach
    void setUp() {
        PetType feline = new PetType();
        feline.setName("Feline");
        feline.setId(101);
        petTypes = new ArrayList<PetType>();
        petTypes.add(feline);
    }

    @Test
    void findPetTypes() {

        //given
        BDDMockito.given(petRepository.findPetTypes()).willReturn(petTypes);
        //when
        Collection<PetType> ret = service.findPetTypes();
        //then
        BDDMockito.then(petRepository).should(BDDMockito.times(1)).findPetTypes();
        Assertions.assertThat(ret).size().isGreaterThan(0);
    }

    @Test
    void findPetTypesException() {

        //given
        DataAccessException ex = Mockito.mock(DataAccessException.class);
        BDDMockito.given(petRepository.findPetTypes()).willThrow(ex);
        //when
        Collection<PetType> ret = null;

        try {
            ret = service.findPetTypes();
        } catch (DataAccessException e) {
            System.out.println("Expected Exception");
        }

        //then
        BDDMockito.then(petRepository).should(BDDMockito.times(1)).findPetTypes();
        //BDDMockito.then(petRepository).should(BDDMockito.never()).findPetTypes();
        //Assertions.assertThat(ret).size().isGreaterThan(0);
    }
}