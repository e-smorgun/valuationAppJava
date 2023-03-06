package com.Controllers;

import com.Controllers.UserController;
import com.Models.Role;
import com.Models.User;
import com.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("test1@example.com", "password1", "Test1", "1234567890", Role.USER));
        users.add(new User("test2@example.com", "password2", "Test2", "0987654321", Role.APPRAISER));

        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAllUsers();
        assertEquals(2, result.size());
        assertEquals("test1@example.com", result.get(0).getEmail());
        assertEquals("password1", result.get(0).getPassword());
        assertEquals("Test1", result.get(0).getName());
        assertEquals("1234567890", result.get(0).getPhoneNumber());
        assertEquals(Role.USER, result.get(0).getRole());

        assertEquals("test2@example.com", result.get(1).getEmail());
        assertEquals("password2", result.get(1).getPassword());
        assertEquals("Test2", result.get(1).getName());
        assertEquals("0987654321", result.get(1).getPhoneNumber());
        assertEquals(Role.APPRAISER, result.get(1).getRole());
    }

    @Test
    public void testGetUserById() {
        User user = new User("test1@example.com", "password1", "Test1", "1234567890", Role.USER);
        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUser(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        User result = responseEntity.getBody();
        assertEquals("test1@example.com", result.getEmail());
        assertEquals("password1", result.getPassword());
        assertEquals("Test1", result.getName());
        assertEquals("1234567890", result.getPhoneNumber());
        assertEquals(Role.USER, result.getRole());
    }

    @Test
    public void testAddUser() {
        User user = new User("test1@example.com", "password1", "Test1", "1234567890", Role.USER);
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        User result = responseEntity.getBody();
        assertEquals("test1@example.com", result.getEmail());
        assertEquals("password1", result.getPassword());
        assertEquals("Test1", result.getName());
        assertEquals("1234567890", result.getPhoneNumber());
        assertEquals(Role.USER, result.getRole());
    }

    @Test
    public void testUpdateUser() {
        User user = new User("test1@example.com", "password1", "Test1", "1234567890", Role.USER);
        when(userService.updateUser(1L, user)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.updateUser(1L, user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        User result = responseEntity.getBody();
        assertEquals("test1@example.com", result.getEmail());
        assertEquals("password1", result.getPassword());
        assertEquals("Test1", result.getName());
        assertEquals("1234567890", result.getPhoneNumber());
        assertEquals(Role.USER, result.getRole());
    }

}