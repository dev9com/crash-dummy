package com.dev9.crash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
class ExceptionController {

    @Autowired
    ApplicationContext context;

    @ExceptionHandler
    public ModelAndView handleError(HttpServletRequest req, Throwable exception)
            throws Throwable {

        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        List<Throwable> exceptions = new ArrayList<>();

        Throwable x = exception;
        while (x != null) {
            exceptions.add(x);
            x = x.getCause();
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptions", exceptions);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date().toString());
        mav.addObject("status", 500);

        mav.setViewName("error");
        return mav;
    }
}
