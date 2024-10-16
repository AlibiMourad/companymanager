package com.sparkers.companymanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sparkers.companymanager.repository.PartnerRepository;
import com.sparkers.companymanager.security.payload.request.SingupRequest;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    private static final String url = "http://localhost:8080/api/auth";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

   /* @Autowired
    private PartnerRepository partnerRepository;
    @Before
    public void before() throws Exception {
        this.action = this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNER));

    }*/
   @Test
   public void signupTest() throws Exception {
       Set<String> role = new HashSet<>();
       role.add("admin");
       SingupRequest singupRequest = new SingupRequest("tuto","tuto@mail.com",role,"1234");

       this.mockMvc.perform(post(url + "/signup")
               .contentType("application/json")
               .content(new Gson().toJson(singupRequest)));
   }
}
