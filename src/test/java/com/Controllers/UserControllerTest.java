//package com.Controllers;
//
//import com.Models.Role;
//import com.Models.User;
//import com.Services.UserService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.junit.jupiter.api.Test;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    private MockMvc mockMvc;
//
//    private List<User> users;
//
//    @BeforeAll
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//
//        users = new ArrayList<>();
//        users.add(new User(1L, "john.doe@example.com", "password", "John Doe", "+1 234-567-8901", Role.USER));
//        users.add(new User(2L, "jane.doe@example.com", "password", "Jane Doe", "+1 234-567-8902", Role.USER));
//    }
//
//    @Test
//    public void getAllUsers_shouldReturnAllUsers() throws Exception {
//        given(userService.getAllUsers()).willReturn(users);
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")))
//                .andExpect(jsonPath("$[0].password", is("password")))
//                .andExpect(jsonPath("$[0].name", is("John Doe")))
//                .andExpect(jsonPath("$[0].phoneNumber", is("+1 234-567-8901")))
//                .andExpect(jsonPath("$[0].role", is("USER")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].email", is("jane.doe@example.com")))
//                .andExpect(jsonPath("$[1].password", is("password")))
//                .andExpect(jsonPath("$[1].name", is("Jane Doe")))
//                .andExpect(jsonPath("$[1].phoneNumber", is("+1 234-567-8902")))
//                .andExpect(jsonPath("$[1].role", is("USER")));
//    }
//
//    @Test
//    public void getAllUsers_shouldReturnEmptyList() throws Exception {
//        given(userService.getAllUsers()).willReturn(new ArrayList<>());
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$", hasSize(0)));
//    }
//
//}
//
//}