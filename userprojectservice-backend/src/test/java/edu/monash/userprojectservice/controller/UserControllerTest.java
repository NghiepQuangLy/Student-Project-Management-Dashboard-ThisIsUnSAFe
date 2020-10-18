package edu.monash.userprojectservice.controller;

import edu.monash.userprojectservice.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
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

    private static String FAMILY_NAME_KEY = "{family_Name}";
    private static String GIVEN_NAME_KEY = "{given_name}";
    private static String EMAIL_KEY = "{email_address}";
    private static String USER_GROUP_KEY = "{user_group}";

    private static String TEST_GIVEN_NAME = "test firstName";
    private static String TEST_FAMILY_NAME = "test lastName";
    private static String TEST_EMAIL = "test email";
    private static String TEST_USER_GROUP = "test user group";

    private static String CREATE_USER_URL = "/create-user";
    private static String GET_USER_URL = "/get-user";

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

    @Nested
    @DisplayName("POST /create-user")
    public class CreateUserTest {
    @Test
    public void shouldSuccessfullyCreateUser() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_success()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailWithoutUserGivenName() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noGivenName()))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFailWithoutFamilyName() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noFamilyNameKey()))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFailWithoutEmail() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noEmail()))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFailWithoutUserGroup() throws Exception {
        mockServer.perform(request(CREATE_USER_URL, userRequestPayload_noUserGroup()))
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
                .replace(FAMILY_NAME_KEY, TEST_GIVEN_NAME)
                .replace(GIVEN_NAME_KEY, TEST_FAMILY_NAME)
                .replace(EMAIL_KEY, TEST_EMAIL)
                .replace(USER_GROUP_KEY, TEST_USER_GROUP);
    }

    private String userRequestPayload_noFamilyNameKey() {
        return templateRequest
                .replace(FAMILY_NAME_KEY, "")
                .replace(GIVEN_NAME_KEY, TEST_FAMILY_NAME)
                .replace(EMAIL_KEY, TEST_EMAIL)
                .replace(USER_GROUP_KEY, TEST_USER_GROUP);
    }

    private String userRequestPayload_noGivenName() {
        return templateRequest
                .replace(FAMILY_NAME_KEY, TEST_GIVEN_NAME)
                .replace(GIVEN_NAME_KEY, "")
                .replace(EMAIL_KEY, TEST_EMAIL)
                .replace(USER_GROUP_KEY, TEST_USER_GROUP);
    }

    private String userRequestPayload_noEmail() {
        return templateRequest
                .replace(FAMILY_NAME_KEY, TEST_GIVEN_NAME)
                .replace(GIVEN_NAME_KEY, TEST_FAMILY_NAME)
                .replace(EMAIL_KEY, "")
                .replace(USER_GROUP_KEY, TEST_USER_GROUP);
    }

    private String userRequestPayload_noUserGroup() {
        return templateRequest
                .replace(FAMILY_NAME_KEY, TEST_GIVEN_NAME)
                .replace(GIVEN_NAME_KEY, TEST_FAMILY_NAME)
                .replace(EMAIL_KEY, "")
                .replace(USER_GROUP_KEY, TEST_USER_GROUP);
    }
}

    @Nested
    @DisplayName("GET /get-user")
    public class GetUserTest {
        @Test
        public void shouldSuccessfullyGetUser() throws Exception {
            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(GET_USER_URL+ "?requestorEmail={email}", "email@test.com");
            mockServer.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void shouldFailWithoutUserEmail() throws Exception {
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_USER_URL);

            mockServer.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        }
    }
}
