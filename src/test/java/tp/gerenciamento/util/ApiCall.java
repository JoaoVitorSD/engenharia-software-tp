package tp.gerenciamento.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class ApiCall {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static final Class<Object> defaultResponseClass = Object.class;

    private ResultMatcher getStatus(Status expected) {
        return switch (expected) {
            case CREATED -> status().isCreated();
            case OK -> status().isOk();
            case NO_CONTENT -> status().isNoContent();
            case BAD_REQUEST -> status().isBadRequest();
            case NOT_FOUND -> status().isNotFound();
        };
    }

    public <T> T post(String url, Object request, Class<T> response, Status expected) throws Exception {
        ResultActions result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(url)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(getStatus(expected));
        return getRequestResponseIfSucess(response, expected, result);
    }

    public <T> T put(String url, Object request, Class<T> response, Status expected) throws Exception {
        ResultActions result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(url)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(getStatus(expected));
        return getRequestResponseIfSucess(response, expected, result);
    }
    private <T> T getRequestResponseIfSucess(Class<T> response, Status expected, ResultActions result) throws JsonProcessingException, UnsupportedEncodingException {
        if (expected.code < 399) {
            return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8), response);
        }
        return null;
    }
    public <T> T get(String url, TypeReference<T> type, Status expected) throws Exception {
        ResultActions result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(url)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
        ).andExpect(getStatus(expected));
        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8), type);
    }

    public void delete(String url, Status expected) throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(url)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
        ).andExpect(getStatus(expected));
    }


}
