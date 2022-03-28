package lv.vladislavs.archiver.filter;

import lv.vladislavs.archiver.model.database.RequestLog;
import lv.vladislavs.archiver.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static lv.vladislavs.archiver.util.HttpReqRespUtil.getIpAddress;

@Component
public class RequestLogFilter implements Filter {
    @Autowired
    RequestLogRepository requestLogRepository;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String ipAddress = getIpAddress(httpServletRequest);
        String servletPath = httpServletRequest.getServletPath();

        requestLogRepository.save(RequestLog.builder()
                .ipAddress(ipAddress)
                .servletPath(servletPath)
                .requestTime(OffsetDateTime.now(ZoneOffset.UTC))
                .build());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}