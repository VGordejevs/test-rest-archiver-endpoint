package lv.vladislavs.archiver.filter;

import lv.vladislavs.archiver.compressor.ArchivingMethod;
import lv.vladislavs.archiver.model.database.RequestLog;
import lv.vladislavs.archiver.repository.RequestLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@SpringBootTest
public class RequestLogFilterIT {
    private final static String ARCHIVER_URL = "/api/v1/archiver/%s";
    private final static String ARCHIVER_ZIP_URL = String.format(ARCHIVER_URL, ArchivingMethod.ZIP);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    RequestLogFilter requestLogFilter;

    @MockBean
    RequestLogRepository requestLogRepository;

    @Test
    public void testRequestLogged() throws Exception {
        MockMultipartFile file = getMockMultipartFile();

        MockMvc mockMvc = getMockMvcWithFilter();
        mockMvc.perform(multipart(ARCHIVER_ZIP_URL)
                        .file(file)
                        .servletPath(ARCHIVER_ZIP_URL));

        ArgumentCaptor<RequestLog> requestLogArgumentCaptor = ArgumentCaptor.forClass(RequestLog.class);
        verify(requestLogRepository, times(1)).save(requestLogArgumentCaptor.capture());

        RequestLog requestLog = requestLogArgumentCaptor.getValue();
        assertNotNull(requestLog);
        assertNotNull(requestLog.getIpAddress());
        assertEquals(ARCHIVER_ZIP_URL, requestLog.getServletPath());
        assertNotNull(requestLog.getRequestTime());
    }

    private MockMvc getMockMvcWithFilter() {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(requestLogFilter)
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
