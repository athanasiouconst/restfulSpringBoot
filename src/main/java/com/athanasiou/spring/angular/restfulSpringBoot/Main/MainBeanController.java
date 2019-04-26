package com.athanasiou.spring.angular.restfulSpringBoot.Main;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class MainBeanController {

    @GetMapping(path = "/main")
    public String mainPage() {
        return "This is main Page from Controller";
    }


    @GetMapping(path = "/main-bean")
    public MainBean mainBean() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new MainBean("Main Page - Bean");
    }

    @GetMapping(path = "/main-bean-custom")
    public MainBean mainBeanCustom() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new MainBean("Main Pge - Bean - Custom");
    }

    @GetMapping(path = "/main-bean/username/{name}")
    public MainBean mainPagePathVariable(@PathVariable String name) {
        return new MainBean(String.format("Main Page, %s", name));
    }
}
