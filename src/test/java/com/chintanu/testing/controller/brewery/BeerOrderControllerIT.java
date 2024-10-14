package com.chintanu.testing.controller.brewery;

import com.chintanu.testing.model.brewery.BeerOrderDto;
import com.chintanu.testing.model.brewery.BeerOrderPagedList;
import com.chintanu.testing.model.brewery.OrderStatusEnum;
import com.chintanu.testing.service.brewery.BeerOrderService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BeerOrderControllerIT {

    @MockBean
    BeerOrderService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void listOrders() throws Exception {

        //given
        UUID customerId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        Integer pageNumber = 2;
        Integer pageSize = 10;
        ArgumentCaptor<UUID> customerIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<PageRequest> pageRequestCaptor = ArgumentCaptor.forClass(PageRequest.class);

        BeerOrderDto beerOrderDto1 = BeerOrderDto.builder()
                .orderStatus(OrderStatusEnum.NEW)
                .id(orderId)
                .customerId(customerId)
                .customerRef("ABC")
                .version(1)
                .build();

        BeerOrderDto beerOrderDto2 = BeerOrderDto.builder()
                .orderStatus(OrderStatusEnum.READY)
                .id(orderId)
                .customerId(customerId)
                .customerRef("XYZ")
                .version(2)
                .build();

        BeerOrderPagedList page = new BeerOrderPagedList(List.of(beerOrderDto1, beerOrderDto2));

        BDDMockito.given(service.listOrders(customerIdCaptor.capture(), pageRequestCaptor.capture())).willReturn(page);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{customerId}/orders", customerId).queryParam("pageNumber", String.valueOf(pageNumber)).queryParam("pageSize", String.valueOf(pageSize)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfElements", Is.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].customerRef", Is.is(beerOrderDto1.getCustomerRef())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].customerId", Is.is(beerOrderDto1.getCustomerId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].orderStatus", Is.is(beerOrderDto1.getOrderStatus().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Is.is(beerOrderDto1.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].version", Is.is(beerOrderDto1.getVersion())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].customerRef", Is.is(beerOrderDto2.getCustomerRef())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].customerId", Is.is(beerOrderDto2.getCustomerId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].orderStatus", Is.is(beerOrderDto2.getOrderStatus().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Is.is(beerOrderDto2.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].version", Is.is(beerOrderDto2.getVersion())));

        //then
        PageRequest req = pageRequestCaptor.getValue();
        Integer pageNumberActual = req.getPageNumber();
        Integer pageSizeActual = req.getPageSize();

        BDDMockito.then(service).should(BDDMockito.times(1)).listOrders(BDDMockito.any(), BDDMockito.any());
        Assertions.assertEquals(2, pageNumberActual);
        Assertions.assertEquals(10, pageSizeActual);

    }

    @Test
    void getOrder() throws Exception {

        //given
        UUID customerId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        ArgumentCaptor<UUID> customerIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> orderIdCaptor = ArgumentCaptor.forClass(UUID.class);
        BeerOrderDto beerOrderDto = BeerOrderDto.builder()
                                                .orderStatus(OrderStatusEnum.NEW)
                                                .id(orderId)
                                                .customerId(customerId)
                                                .customerRef("ABC")
                                                .version(1)
                                                .build();
        BDDMockito.given(service.getOrderById(customerIdCaptor.capture(), orderIdCaptor.capture())).willReturn(beerOrderDto);

        //when
         mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{customerId}/orders/{orderId}", customerId, orderId))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andExpect(MockMvcResultMatchers.jsonPath("$.customerRef", Is.is(beerOrderDto.getCustomerRef())))
                                    .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Is.is(beerOrderDto.getCustomerId().toString())))
                                    .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", Is.is(beerOrderDto.getOrderStatus().toString())))
                                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(beerOrderDto.getId().toString())))
                                    .andExpect(MockMvcResultMatchers.jsonPath("$.version", Is.is(beerOrderDto.getVersion().toString())));

        //then
        BDDMockito.then(service).should(BDDMockito.times(1)).getOrderById(BDDMockito.any(), BDDMockito.any());
    }
}