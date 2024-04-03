package hello.login.Web.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, logId);

        //@RequestMapping -> HandleMethod
        //정적 리소스가 호출되는 경우에는 ResourceHttpRequestHandler가 호출된다.
        //정적 리소스가 호출되는 경우는 제외하고 @Controller, @RequestMapping을 사용할 경우 핸들러 정보를 얻기 위해 if문 사용
        if(handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
        }

        log.info("REQUEST [{}][{}][{}]", logId, requestURI, handler); //handler: 어떤 컨트롤러가 호출되는지 확인할 수 있음
        return true; //false 일 경우 컨트롤러 호출없이 여기서 끝남.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //예외가 발생해도 afterCompletion은 실행 됨.
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);

        log.info("RESPONSE [{}][{}]", logId, requestURI);

        if(ex != null) {
            log.error("afterCompletion error !!", ex);
        }
    }
}
