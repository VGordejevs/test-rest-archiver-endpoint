package lv.vladislavs.archiver.controller;

import lv.vladislavs.archiver.compressor.ArchivingMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ArchiverControllerIT {
    private final static String ARCHIVER_URL = "/api/v1/archiver/%s";
    private final static String ARCHIVER_ZIP_URL = String.format(ARCHIVER_URL, ArchivingMethod.ZIP);
    private final static String UNKNOWN_ARCHIVING_METHOD = "not-existing-archiving-method";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testArchiverSuccess() throws Exception {
        MockMultipartFile file = getMockMultipartFile();

        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(multipart(ARCHIVER_ZIP_URL).file(file))
                .andExpect(status().isOk());
    }

    @Test
    public void testArchivingMethodNotFound() throws Exception {
        MockMultipartFile file = getMockMultipartFile();

        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(multipart(String.format(ARCHIVER_URL, UNKNOWN_ARCHIVING_METHOD)).file(file))
                .andDo(print())
                .andExpect(jsonPath("$.message").value(containsString(UNKNOWN_ARCHIVING_METHOD)))
                .andExpect(status().isBadRequest());
    }

    private MockMvc getMockMvc() {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    private static MockMultipartFile getMockMultipartFile() {
        return new MockMultipartFile(
                "files",
                "file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Test file content".getBytes()
        );
    }
}
