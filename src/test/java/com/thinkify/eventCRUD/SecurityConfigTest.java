package com.thinkify.eventCRUD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Base64;



@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    private String base64Credentials(String username, String password) {
        String credentials = username + ":" + password;
        System.out.println(credentials);
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }

    @Test
    public void testUnauthenticatedAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/event"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testAuthenticatedAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/event")
                .header("Authorization","Basic " + base64Credentials("Deepak","pwd1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
