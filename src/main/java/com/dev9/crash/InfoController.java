package com.dev9.crash;

import com.dev9.crash.info.ClassFileLocator;
import com.dev9.crash.info.WebServerInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class InfoController {

    @Autowired
    ApplicationContext context;

    @Autowired
    ServletContext servletContext;

    @RequestMapping("/server")
    String server(HttpServletRequest request, Model model) {

        WebServerInformation info = new WebServerInformation();

        model.addAttribute("info", info.allProperties(request, servletContext));

        return "server";
    }


    @RequestMapping("/class")
    String server(@RequestParam(value = "clazz", required = false) String clazz, Model model) {

        ClassFileLocator classFileLocator = new ClassFileLocator();

        String search = "java.lang.String";
        if (clazz != null)
            search = clazz;

        model.addAttribute("clazz", search);
        model.addAttribute("clazzLocation", classFileLocator.find(search));

        return "clazz";
    }

}
