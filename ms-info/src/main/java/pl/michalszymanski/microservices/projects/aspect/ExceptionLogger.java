package pl.michalszymanski.microservices.projects.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogger {

    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionLogger.class);

    @Before(value = "" +
            "@within(org.springframework.web.bind.annotation.RestControllerAdvice) " +
            "&& " +
            "execution(* *.*(..)) && args(exception)")
    public void logException(Exception exception){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.error("Some exception occurred", exception);
        } else {
            StackTraceElement firstStackTrace = exception.getStackTrace()[0];
            String sourceClass = firstStackTrace.getClassName();
            String methodName = firstStackTrace.getMethodName();
            int line = firstStackTrace.getLineNumber();
            LOGGER.error("Exception type: {}, message: '{}', source: {}#{} at line {}",
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    sourceClass,
                    methodName,
                    line);
        }
    }
}
