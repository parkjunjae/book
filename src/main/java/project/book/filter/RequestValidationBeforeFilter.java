package project.book.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationBeforeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (null != header) {
            header = header.trim();
            if (StringUtils.startsWithIgnoreCase(header,"Basic ")) {
               byte[] base64Token =  header.substring(6).getBytes(StandardCharsets.UTF_8);
               byte[] decoded;
               try {
                   decoded = Base64.getDecoder().decode(base64Token);
                   String token = new String(decoded, StandardCharsets.UTF_8); // un:pwd
                   int delim = token.indexOf(":");
                   if (delim == -1){
                       throw new BadCredentialsException("토큰이 없다.");
                   }
                   String email = token.substring(0, delim);
                   if (email.toLowerCase().contains("test")) {
                       res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                       return;
                   }
               }catch (IllegalArgumentException e) {
                   throw new BadCredentialsException("잘못된 토큰이다.");

               }
            }

            chain.doFilter(request,response);

        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
