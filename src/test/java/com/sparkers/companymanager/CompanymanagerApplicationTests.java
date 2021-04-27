package com.sparkers.companymanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkers.companymanager.model.Partner;
import com.sparkers.companymanager.repository.PartnerRepository;
//import io.vavr.control.Try;

import com.sparkers.companymanager.services.PartnerService;
import javafx.application.Application;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CompanyManagerApplicationTests {
    private static final String url = "http://localhost:8080/api/partners/";

    static final String dumpRef = new Timestamp(new Date().getTime()).toString();
    static final String dumpRef2 = new Timestamp(new Date().getTime()).toString();
    static final String dumpRef3 = new Timestamp(new Date().getTime()).toString();
    private static final String PARTNERch1 = "{\"name\": \"Bells & Whistles\",\"reference\": \"";
    private static final String PARTNERch2 = "\"" + ",\"locale\": \"en_EN\"," + "\"expirationTime\": \"2021-04-24T16:54:25.000+00:00\"}";
    private static final String PARTNER2 = "{\"name\": \"Calvin Clark\",\"reference\": " +
            "\"" +
            dumpRef2 +
            "\"" +
            ",\"locale\": \"de_DE\"," +
            "\"expirationTime\": \"2017-10-03T12:18:46.000+00:00\"}";
    private static final String PARTNER3 = "{\"name\": \"Bells\",\"reference\": " +
            "\"" +
            dumpRef3 +
            "\"" +
            ",\"locale\": \"fr_FR\"," +
            "\"expirationTime\": \"2016-10-03T12:18:46.000+00:00\"}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private PartnerRepository partnerRepository;


    @Before
    public void before() throws Exception {
       /* this.action = this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNER));*/
    }

    @Test
    public void testGetAllPartner() throws Exception {
        this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx1" + PARTNERch2));
        this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx2" + PARTNERch2));
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    public void testAddPartner() throws Exception {
        this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx3" + PARTNERch2))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Bells & Whistles")))
                .andExpect(jsonPath("$.reference", Matchers.equalTo("xxxxx3")))
                .andExpect(jsonPath("$.locale", Matchers.equalTo("en_EN")))
                .andExpect(jsonPath("$.expirationTime", Matchers.equalTo("2021-04-24T16:54:25.000+00:00")));
        this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx3" + PARTNERch2))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPartnerById() throws Exception {
        ResultActions action = this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx4" + PARTNERch2));

        Partner newPartner = mapper.readValue(action.andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(), Partner.class);

        this.mockMvc.perform(get(url + newPartner.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Bells & Whistles")))
                .andExpect(jsonPath("$.reference", Matchers.equalTo("xxxxx4")))
                .andExpect(jsonPath("$.locale", Matchers.equalTo("en_EN")))
                .andExpect(jsonPath("$.expirationTime", Matchers.equalTo("2021-04-24T16:54:25.000+00:00")));
    }

    @Test
    public void testUpdatePartnerById() throws Exception {
        ResultActions action = this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx5" + PARTNERch2));
        Partner newPartner = mapper.readValue(action.andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(), Partner.class);

        this.mockMvc.perform(put(url + newPartner.getId())
                .contentType("application/json")
                .content(PARTNER2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Calvin Clark")))
                .andExpect(jsonPath("$.reference", Matchers.equalTo(dumpRef2)))
                .andExpect(jsonPath("$.locale", Matchers.equalTo("de_DE")))
                .andExpect(jsonPath("$.expirationTime", Matchers.equalTo("2017-10-03T12:18:46.000+00:00")));
    }


    @Test
    public void testDeletePartner() throws Exception {
      ResultActions action = this.mockMvc.perform(post(url)
                .contentType("application/json")
                .content(PARTNERch1 + "xxxxx6" + PARTNERch2));

        Partner newPartner = mapper.readValue(action.andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(), Partner.class);

        this.mockMvc.perform(delete(url + newPartner.getId()))
                .andExpect(status().isOk());
    }

}
