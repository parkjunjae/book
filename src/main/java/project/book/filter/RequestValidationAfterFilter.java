//package project.book.filter;
//
//import jakarta.servlet.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.io.IOException;
//
//@Slf4j
//public class RequestValidationAfterFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            log.info("User" + authentication.getName() + "성공 후 인증 과 인가" + authentication.getAuthorities().toString());
//        }
//        chain.doFilter(request,response);
//
//    }
//}
