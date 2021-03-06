package lv.vladislavs.archiver.util;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static lv.vladislavs.archiver.util.HttpReqRespUtil.getIpAddress;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpReqRespUtilTest {
    private final static String HEADER_HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private final static String CLIENT_IP = "127.0.0.1";

    @Test
    void testGetIpAddressIfHeaderPresent() {
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getHeader(HEADER_HTTP_CLIENT_IP)).thenReturn(CLIENT_IP);

        assertEquals(CLIENT_IP, getIpAddress(httpServletRequestMock));
    }

    @Test
    void testGetIpAddressIfNoHeaderPresent() {
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getHeader(anyString())).thenReturn(null);
        when(httpServletRequestMock.getRemoteAddr()).thenReturn(CLIENT_IP);

        assertEquals(CLIENT_IP, getIpAddress(httpServletRequestMock));
    }
}