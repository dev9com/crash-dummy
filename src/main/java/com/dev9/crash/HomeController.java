package com.dev9.crash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMethod;
=======
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> origin/master

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext context;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @ModelAttribute("allBadThings")
    List<BadThing> allBadThings() {
        List<BadThing> result = new ArrayList<>(allBadThingsMap().values());

        return result;
    }

    Map<String, BadThing> allBadThingsMap() {
        return context.getBeansOfType(BadThing.class);
    }

<<<<<<< HEAD
    BadThing byId(String id) {
        for (BadThing badThing : allBadThings()) {
            if (badThing.getBadThingId().equalsIgnoreCase(id))
                return badThing;
        }
        return null;
    }

    private String result;

    @RequestMapping(value = "/crash", method = RequestMethod.POST)
    String crash(String id) throws Exception {
        BadThing crash = byId(id);
        if (crash != null) {
            log.info("Started {}", id);
            result = crash.doBadThing();
            log.info("Finished {}", id);
        } else {
            log.info("Can't find {}", id);
        }

        return "crash";
    }

    @ModelAttribute("result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
=======
    @RequestMapping("/crash")
    String crash(@RequestParam(value = "id") String id, Model model, HttpServletRequest request) throws Exception {

        System.out.println("Got a crash request for " + id);

        BadThing found = null;

        for (BadThing bad : allBadThingsMap().values()) {
            if (bad.getBadThingId().compareToIgnoreCase(id) == 0)
                found = bad;
        }

        if (found == null) {
            model.addAttribute("crash", "Can't find " + id);
            return "crash";
        }

        found.setHttpRequest(request);

        String result = found.doBadThing();

        if (found.doBadThing() != null)
            model.addAttribute("crash", "Crash " + id + "returned" + result);
        else
            model.addAttribute("crash", "Run " + id + " successfully.");

        return "crash";
>>>>>>> origin/master
    }
}
