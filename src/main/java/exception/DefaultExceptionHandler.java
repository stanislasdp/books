package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultResolver(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("text", ex.getMessage());
        return modelAndView;
    }
}
