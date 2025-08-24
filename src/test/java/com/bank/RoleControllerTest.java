package com.bank;

import com.bank.controller.RoleController;
import com.bank.model.Role;
import com.bank.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private Model model;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowRoleForm() {
        String viewName = roleController.showRoleForm(model);
        assertEquals("roleForm", viewName);
    }

    @Test
    void testSaveRole() {
        Role role = new Role(1L, "ADMIN");
        String result = roleController.saveRole(role);
        assertEquals("redirect:/roles/list", result);
        verify(roleService, times(1)).saveRole(role);
    }

    @Test
    void testListRoles() {
        List<Role> roles = Arrays.asList(new Role(1L, "ADMIN"), new Role(2L, "USER"));
        when(roleService.getAllRoles()).thenReturn(roles);

        String viewName = roleController.listRoles(model);

        assertEquals("roleList", viewName);
        verify(model, times(1)).addAttribute("roles", roles);
    }

    @Test
    void testEditRole() {
        Role role = new Role(1L, "MANAGER");
        when(roleService.getRoleById(1L)).thenReturn(role);

        String viewName = roleController.editRole(1L, model);

        assertEquals("roleForm", viewName);
        verify(model, times(1)).addAttribute("role", role);
    }

    @Test
    void testDeleteRole() {
        String result = roleController.deleteRole(1L);
        assertEquals("redirect:/roles/list", result);
        verify(roleService, times(1)).deleteRole(1L);
    }
}
