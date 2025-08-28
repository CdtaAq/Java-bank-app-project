package com.bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Bring up full app + MockMvc
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void adminCanAccessRolesForm() throws Exception {
        mockMvc.perform(get("/roles/form"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void normalUserCannotAccessRolesForm() throws Exception {
        mockMvc.perform(get("/roles/form"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void adminCanAccessBranchForm() throws Exception {
        mockMvc.perform(get("/branches/form"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void normalUserCannotAccessBranchForm() throws Exception {
        mockMvc.perform(get("/branches/form"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void adminCanSeeUsersList() throws Exception {
        mockMvc.perform(get("/users/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void normalUserCannotSeeUsersList() throws Exception {
        mockMvc.perform(get("/users/list"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void normalUserCanAccessUserFormOwnProfile() throws Exception {
        // user form is available to users (their profile): /users/form should be accessible
        mockMvc.perform(get("/users/form"))
                .andExpect(status().isOk());
    }
}
