package com.example.demo.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController
{
    @GetMapping(path = "/filtering")
    public FilteredBean getSomeFilteredBean()
    {
        return new FilteredBean("value1", "value2", "value3");
    }
}
