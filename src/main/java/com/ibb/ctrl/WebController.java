/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.ctrl;

import com.ibb.model.MyList;
import com.ibb.model.Person;
import com.ibb.service.MyService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author trainer
 */
@Controller
@RequestMapping(path = "/web")
@Data
public class WebController {

    @Autowired
    private MyService myService;

    private MyList myList;

    @Value("${mygreeting}")
    private String greeting;

    public WebController(MyList myList) {
        this.myList = myList;
        System.out.println("Controller wurde instantiiert!");
    }

    @PostConstruct
    public void init() {
        System.out.println("" + greeting);
    }

    // @RequestMapping(path = "/hello",method = {RequestMethod.GET})
    @GetMapping(path = "/hello")
    public String doSomething(Model model) {

        Person p = new Person("Johm", "Doe", "New York");

        model.addAttribute("meinText", "Das ist die Begrüssung!");
        model.addAttribute("person", p);

        return "index";
    }

    // @RequestMapping(path = "/hello",method = {RequestMethod.GET})
    @GetMapping(path = "/book")
    public String getBookPage(Model model) {

        Person p = new Person("Johm", "Doe", "New York");

        model.addAttribute("meinText", "Das ist die Begrüssung!");
        model.addAttribute("person", p);

        return "book";
    }

    @GetMapping(path = "/hello1")
    public ModelAndView doSomething() {
        ModelAndView mv = new ModelAndView("index.xhtml");
        Person p = new Person("Johm", "Doe", "New York");

        mv.addObject("meinText", "Das ist die Begrüssung!");
        mv.addObject("person", p);

        return mv;
    }

    @GetMapping(path = "/listPerson")
    public ModelAndView doList() {
        ModelAndView mv = new ModelAndView("index.xhtml");

        mv.addObject("meinText", "Das ist die Begrüssung!");
        mv.addObject("personen", myService.listPersonen());

        return mv;
    }

    @GetMapping("/change-language")
    public ModelAndView changeLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam("lang") String lang) {
        ModelAndView m = new ModelAndView("index.xhtml");
        System.out.println("Session ID:" + request.getSession().getId() + "mit dem Browser:" + request.getHeader("User-Agent"));
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        //localeResolver.setLocale(request, response,  Locale.forLanguageTag(lang));
//        if (localeResolver != null) {
//            if (!localeResolver.resolveLocale(request).equals(Locale.forLanguageTag(lang))) {
//                personLists.putIfAbsent(localeResolver.resolveLocale(request), personen);
//                localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));
//                if (personLists.get(new Locale(lang)) ==  null) personLists.put(Locale.forLanguageTag(lang), myService.createPersonen(Locale.forLanguageTag(lang)));
//                m.addObject("people", personLists.get(new Locale(lang)));
//            } else {
        //    m.addObject("people", allPersonen.get(lang));
//            }
//        }
        return m;
    }

}
