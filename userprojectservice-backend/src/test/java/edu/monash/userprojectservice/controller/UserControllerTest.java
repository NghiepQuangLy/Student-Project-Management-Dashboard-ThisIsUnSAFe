package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private static String FIRST_NAME_KEY = "{firstName}";
    private static String LAST_NAME_KEY = "{lastName}";
    private static String EMAIL_KEY = "{email}";

    private static String TEST_FIRST_NAME = "test firstName";
    private static String TEST_LAST_NAME = "test lastName";
    private static String TEST_EMAIL = "test email";

    private static String CREATE_USER_URL = "/create-user";

    private static String templateRequest;

    private MockMvc mockServer;

    @Mock
    private UserService userService;

    @BeforeAll
    public static void setup() throws IOException {
        templateRequest = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/createUser.json")), "UTF-8");
    }

    @BeforeEach
    public void setupEach() {
        mockServer = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    public void shouldSuccessfullyCreateUser() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_success()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailWithoutUserFrisName() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noFirstName()))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

        @Test
    public void shouldFailWithoutLastName() throws Exception {
            mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noLastName()))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFailWithoutEmail() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noEmail()))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    private MockHttpServletRequestBuilder request(String url, String payload) {
        return MockMvcRequestBuilders.post(url)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);
    }

    private String userRequestPayload_success() {
        return templateRequest
                .replace(FIRST_NAME_KEY, TEST_FIRST_NAME)
                .replace(LAST_NAME_KEY, TEST_LAST_NAME)
                .replace(EMAIL_KEY, TEST_EMAIL);
    }

    private String userRequestPayload_noFirstName() {
        return templateRequest
                .replace(FIRST_NAME_KEY, "")
                .replace(LAST_NAME_KEY, TEST_LAST_NAME)
                .replace(EMAIL_KEY, TEST_EMAIL);
    }

    private String userRequestPayload_noLastName() {
        return templateRequest
                .replace(FIRST_NAME_KEY, TEST_FIRST_NAME)
                .replace(LAST_NAME_KEY, "")
                .replace(EMAIL_KEY, TEST_EMAIL);
    }

    private String userRequestPayload_noEmail() {
        return templateRequest
                .replace(FIRST_NAME_KEY, TEST_FIRST_NAME)
                .replace(LAST_NAME_KEY, TEST_LAST_NAME)
                .replace(EMAIL_KEY, "");
    }
}
