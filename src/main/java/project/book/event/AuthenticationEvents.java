package project.book.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent){
        log.info("로그인 성공 :{}", successEvent.getAuthentication().getName());

    }

    @EventListener
    public void onFailed(AbstractAuthenticationFailureEvent failureEvent){
        log.info("로그인 실패 : {}",failureEvent.getAuthentication().getName()
        , failureEvent.getException().getMessage());
    }

}
