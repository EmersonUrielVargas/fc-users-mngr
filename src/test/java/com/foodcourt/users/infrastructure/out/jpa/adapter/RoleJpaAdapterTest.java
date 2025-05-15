package com.foodcourt.users.infrastructure.out.jpa.adapter;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.infrastructure.out.jpa.entity.RoleEntity;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleJpaAdapterTest {

    @Mock
    private IRoleRepository roleRepository;
    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;


    @Test
    void shouldFoundRolByName() {
        UserRole inputRole = UserRole.OWNER;
        String rolNameDb = IRoleEntityMapper.mapUserRoleToString(inputRole);
        RoleEntity mockEntity = new RoleEntity();
        Role role = Role.builder().name(inputRole).build();

        when(roleRepository.findByNombre(rolNameDb)).thenReturn(Optional.of(mockEntity));
        when(roleEntityMapper.toRole(mockEntity)).thenReturn(role);

        Optional<Role> result = roleJpaAdapter.getByName(inputRole);

        assertTrue(result.isPresent());
        assertEquals(role, result.get());
        verify(roleRepository).findByNombre(rolNameDb);
        verify(roleEntityMapper).toRole(mockEntity);
    }

    @Test
    void shouldNotFoundRolByName() {
        UserRole inputRole = UserRole.OWNER;
        String rolNameDb = IRoleEntityMapper.mapUserRoleToString(inputRole);

        when(roleRepository.findByNombre(rolNameDb)).thenReturn(Optional.empty());

        Optional<Role> result = roleJpaAdapter.getByName(inputRole);

        assertTrue(result.isEmpty());
        verify(roleRepository).findByNombre(rolNameDb);
        verifyNoInteractions(roleEntityMapper);
    }


    @Test
    void shouldFoundRoleById() {
        Long id = 1L;
        RoleEntity mockEntity = new RoleEntity();
        Role roleFound = Role.builder().id(id).build();

        when(roleRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        when(roleEntityMapper.toRole(mockEntity)).thenReturn(roleFound);

        Optional<Role> result = roleJpaAdapter.getById(id);

        assertTrue(result.isPresent());
        assertEquals(roleFound, result.get());
        verify(roleRepository).findById(id);
        verify(roleEntityMapper).toRole(mockEntity);
    }

    @Test
    void shouldNotFoundRoleById() {
        Long id = 1L;
        when(roleRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Role> result = roleJpaAdapter.getById(id);

        assertTrue(result.isEmpty());
        verify(roleRepository).findById(id);
        verifyNoInteractions(roleEntityMapper);
    }
}