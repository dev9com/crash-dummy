package com.dev9.crash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
class HomeController {

    @Autowired
    ApplicationContext context;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @ModelAttribute("allBadThings")
    List<BadThing> allBadThings() {
        List result = new ArrayList<>(allBadThingsMap().values());

        return result;
    }

    Map<String, BadThing> allBadThingsMap() {
        return context.getBeansOfType(BadThing.class);
    }

    @RequestMapping(value = "/crash", params = {"id"})
    String crash(String id) throws Exception {
        BadThing crash = allBadThingsMap().get(id);
        if (crash != null) {
            return crash.doBadThing();
        } else {
            return null;
        }
    }
}
