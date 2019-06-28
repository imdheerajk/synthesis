package com.programming.exercise.synthesis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.exercise.synthesis.controller.UserController;
import com.programming.exercise.synthesis.entity.User;
import com.programming.exercise.synthesis.service.UserService;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserTest() throws Exception{
        User user=  new User(1,"Dheeraj","dhk93@gmail.com","123 main street regina ");
        when(userService.getUser(1)).thenReturn( Optional.of(user));
        RequestBuilder request= MockMvcRequestBuilders.get("/user/{userId}",1).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(request).
                andExpect(content().json("{userId:1,name:Dheeraj}")).
                andReturn();
        mvcResult.getResponse();
    }

    /**
     * <p>  Following method test validation of  email.
     *
     * </p>
     * */
    @Test
    public void userEmailValidationTest()  throws  Exception  {
        User user=new User(90,"dheeraj","dhk.com"," regina");
        when(userService.makeUser(user)).thenReturn(user);
        RequestBuilder request= MockMvcRequestBuilders.post("/user").
                accept(MediaType.APPLICATION_JSON).content(asJsonString(user)).
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
