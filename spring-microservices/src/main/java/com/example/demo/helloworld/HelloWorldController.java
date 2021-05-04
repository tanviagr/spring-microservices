package com.example.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController
{
//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String getHelloWorld()
    {
        return "Hey! Hi World! I am ready for my grawp!!";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean getHelloWorldBean()
    {
        return new HelloWorldBean("This is my grawp, my yell, from within a bean!");
    }

    @GetMapping(path = "hello-world/your-name/{name}")
    public HelloWorldBean getYourHelloWorldBean(@PathVariable String name)
    {
        return new HelloWorldBean(String.format("Hi Grawp, %s!", name));
    }
}
