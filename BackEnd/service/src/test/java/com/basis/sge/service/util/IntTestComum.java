package com.basis.sge.service.util;

import com.basis.sge.service.ServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

@SpringBootTest(classes = ServiceApplication.class)
@ExtendWith(SpringExtension.class)
public abstract class IntTestComum {

    @Autowired
    private EntityManager em;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    protected EntityManager getEm() {
        return em;
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}