package com.chintanu.testing.service;

import com.chintanu.testing.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepository repository;

    @InjectMocks
    PersonServiceImpl service;

    @Test
    void testingMockVerify() {

        service.testingMockVerify();
        Mockito.verify(repository, Mockito.times(1)).testingMockVerify();
    }

    @Test
    void testGetId() {

        Mockito.when(repository.getId()).thenReturn(34);
        int ret = service.getId();
        System.out.println(ret);
        Mockito.verify(repository, Mockito.times(1)).getId();
        Assertions.assertEquals(34, ret);
    }

    @Test
    void testGetSquare() {

        Mockito.when(repository.getSquare(ArgumentMatchers.anyInt())).thenReturn(121);
        int ret = service.getSquare(anyInt());
        System.out.println(ret);
        Mockito.verify(repository, Mockito.times(1)).getSquare(ArgumentMatchers.anyInt());
        Assertions.assertEquals(121, ret);
    }

    @Test
    void testGetSquareCaptor() {

        ArgumentCaptor<Integer> squareCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(repository.getSquare(squareCaptor.capture())).thenReturn(121);
        int ret = service.getSquare(11);
        System.out.println(ret);
        Mockito.verify(repository, Mockito.times(1)).getSquare(anyInt());
        Assertions.assertEquals(11, squareCaptor.getValue());
        Assertions.assertEquals(121, ret);
    }

    @Test
    void testingMockVerifyBDD() {

        //given - none

        //when
        service.testingMockVerify();

        //then
        BDDMockito.then(repository).should(Mockito.times(1)).testingMockVerify();
    }

    @Test
    void testGetIdBDD() {

        //given
        BDDMockito.given(repository.getId()).willReturn(34);

        //when
        int ret = service.getId();

        //then
        System.out.println(ret);
        BDDMockito.then(repository).should(BDDMockito.times(1)).getId();
        Assertions.assertEquals(34, ret);
    }

    @Test
    void testGetSquareBDD() {

        //given
        BDDMockito.given(repository.getSquare(BDDMockito.anyInt())).willReturn(121);

        //when
        int ret = service.getSquare(11);

        //then
        System.out.println(ret);
        BDDMockito.then(repository).should(BDDMockito.times(1)).getSquare(BDDMockito.anyInt());
        Assertions.assertEquals(121, ret);
    }
}