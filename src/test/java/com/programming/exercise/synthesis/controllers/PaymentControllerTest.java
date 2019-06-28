package com.programming.exercise.synthesis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.exercise.synthesis.controller.PaymentController;
import com.programming.exercise.synthesis.entity.Payment;
import com.programming.exercise.synthesis.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 *
 * Author: Dheeraj
 *
 * Payment controller rest API mockito test class
 */

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PaymentService paymentService;

    @Test
    public void getPaymentTest() throws Exception{
        String cvv="789";
        String creditCard="1234567890123456";
        String address="3456 Main Street Regina";
        Integer paymentId=777;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 100);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        when(paymentService.getPayment(paymentId)).thenReturn( Optional.of(payment));
        RequestBuilder request= MockMvcRequestBuilders.get("/payment/{paymentId}",paymentId).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(request).
                andExpect(content().json("{\n" +
                        "    \"paymentId\": 777,\n" +
                        "    \"paymentAddress\": \"3456 Main Street Regina\",\n" +
                        "    \"creditCard\": \"1234567890123456\",\n" +
                        "    \"cvv\": \"789\"\n" +

                        "}")).
                andReturn();
        mvcResult.getResponse();
    }


    private void validateField(Payment payment) throws  Exception{

        when(paymentService.makePayment(payment)).thenReturn(payment);
        RequestBuilder request= MockMvcRequestBuilders.post("/payment").
                accept(MediaType.APPLICATION_JSON).content(asJsonString(payment)).
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    //address can not be null
    @Test
    public void addressValidationTest() throws  Exception{

        String cvv="123";
        String creditCard="1234567890123456";
        String address=null;
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        validateField(payment);

    }

    //credit card length should be 16
    @Test
    public void creditValidationTest() throws  Exception{

        String cvv="123";
        String creditCard="1234567890";
        String address="6904 knight street";
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        when(paymentService.makePayment(payment)).thenReturn(payment);
        RequestBuilder request= MockMvcRequestBuilders.post("/payment").
                accept(MediaType.APPLICATION_JSON).content(asJsonString(payment)).
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());


    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

