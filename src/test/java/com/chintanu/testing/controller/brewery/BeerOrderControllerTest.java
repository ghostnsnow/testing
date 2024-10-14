package com.chintanu.testing.controller.brewery;

import com.chintanu.testing.model.brewery.BeerOrderDto;
import com.chintanu.testing.model.brewery.BeerOrderPagedList;
import com.chintanu.testing.service.brewery.BeerOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BeerOrderControllerTest {

    @InjectMocks
    BeerOrderController controller;

    @Mock
    BeerOrderService service;

    @Test
    void listOrdersInvalidSize() {

        //given
        Integer pageNumber = -1;
        Integer pageSize = -1;
        UUID customerId = UUID.randomUUID();
        ArgumentCaptor<UUID> customerIdCaptor = ArgumentCaptor.forClass(UUID.class);
        /*ArgumentCaptor<Integer> pageNumCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> pageSizeCaptor = ArgumentCaptor.forClass(Integer.class);*/
        ArgumentCaptor<PageRequest> pageRequestCaptor = ArgumentCaptor.forClass(PageRequest.class);
        BeerOrderPagedList given = Mockito.mock(BeerOrderPagedList.class);

        BDDMockito.given(service.listOrders(customerIdCaptor.capture(), pageRequestCaptor.capture())).willReturn(given);//PageRequest.of(pageNumCaptor.capture(), pageSizeCaptor.capture()))).willReturn(given);

        //when
        controller.listOrders(customerId, pageNumber, pageSize);

        //then
        PageRequest req = pageRequestCaptor.getValue();
        Integer pageNumberActual = req.getPageNumber();
        Integer pageSizeActual = req.getPageSize();
        /*Integer pageNumberActual = pageNumCaptor.getValue();
        Integer pageSizeActual = pageSizeCaptor.getValue();*/

        BDDMockito.then(service).should(BDDMockito.times(1)).listOrders(BDDMockito.any(), BDDMockito.any());
        Assertions.assertEquals(0, pageNumberActual);
        Assertions.assertEquals(25, pageSizeActual);
    }

    @Test
    void listOrdersValidSize() {

        //given
        Integer pageNumber = 2;
        Integer pageSize = 10;
        UUID customerId = UUID.randomUUID();
        ArgumentCaptor<UUID> customerIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<PageRequest> pageRequestCaptor = ArgumentCaptor.forClass(PageRequest.class);

        BeerOrderPagedList given = Mockito.mock(BeerOrderPagedList.class);

        BDDMockito.given(service.listOrders(customerIdCaptor.capture(), pageRequestCaptor.capture())).willReturn(given);

        //when
        controller.listOrders(customerId, pageNumber, pageSize);

        //then
        PageRequest req = pageRequestCaptor.getValue();
        Integer pageNumberActual = req.getPageNumber();
        Integer pageSizeActual = req.getPageSize();

        BDDMockito.then(service).should(BDDMockito.times(1)).listOrders(BDDMockito.any(), BDDMockito.any());
        Assertions.assertEquals(2, pageNumberActual);
        Assertions.assertEquals(10, pageSizeActual);
    }

    @Test
    void getOrder() {

        //given
        BeerOrderDto given = BeerOrderDto.builder().build();
        ArgumentCaptor<UUID> customerIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> orderIdCaptor = ArgumentCaptor.forClass(UUID.class);
        UUID customerId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();

        BDDMockito.given(service.getOrderById(customerIdCaptor.capture(), orderIdCaptor.capture())).willReturn(given);

        //when
        BeerOrderDto ret = controller.getOrder(customerId, orderId);

        //then
        BDDMockito.then(service).should(BDDMockito.times(1)).getOrderById(BDDMockito.any(UUID.class), BDDMockito.any(UUID.class));
        Assertions.assertNotNull(ret);
        Assertions.assertEquals(customerId, customerIdCaptor.getValue());
        Assertions.assertEquals(orderId, orderIdCaptor.getValue());
    }
}