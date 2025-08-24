package com.bank;

import com.bank.model.Role;
import com.bank.repository.RoleRepository;
import com.bank.service.RoleService;
import com.bank.service.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRole() {
        Role role = new Role(1L, "ADMIN");

        when(roleRepository.save(role)).thenReturn(role);

        Role savedRole = roleService.saveRole(role);

        assertNotNull(savedRole);
        assertEquals("ADMIN", savedRole.getRoleName());
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testGetAllRoles() {
        Role role1 = new Role(1L, "ADMIN");
        Role role2 = new Role(2L, "USER");

        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));

        List<Role> roles = roleService.getAllRoles();

        assertEquals(2, roles.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void testGetRoleById() {
        Role role = new Role(1L, "MANAGER");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role foundRole = roleService.getRoleById(1L);

        assertNotNull(foundRole);
        assertEquals("MANAGER", foundRole.getRoleName());
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteRole() {
        Long roleId = 1L;

        doNothing().when(roleRepository).deleteById(roleId);

        roleService.deleteRole(roleId);

        verify(roleRepository, times(1)).deleteById(roleId);
    }
}
