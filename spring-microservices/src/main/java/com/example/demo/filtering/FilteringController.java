package com.example.demo.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController
{
//    only want to return field1, field2
    @GetMapping(path = "/filteringField3")
    public MappingJacksonValue getField1AndField2()
    {
        FilteredBean bean = new FilteredBean("value1", "value2", "value3");
        MappingJacksonValue mapping = new MappingJacksonValue(bean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(path = "/filteringField1And2")
    public MappingJacksonValue getField3()
    {
        FilteredBean bean = new FilteredBean("value1", "value2", "value3");
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue getFilteredFields()
    {
        List<FilteredBean> beanList = Arrays.asList(
                new FilteredBean("value1", "value2", "value3"),
                new FilteredBean("value11", "value12", "value13"),
                new FilteredBean("value111", "value222", "value333")
        );
        MappingJacksonValue mapping = new MappingJacksonValue(beanList);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(path = "/filter-full-view")
    public MappingJacksonValue getAllObjects()
    {
        List<FilteredBean> fullObjects = Arrays.asList(
                new FilteredBean("value1", "value2", "value3"),
                new FilteredBean("value11", "value12", "value13"),
                new FilteredBean("value111", "value222", "value333")
        );
//        since we have defined a bean level filter - we have to specify it.
        MappingJacksonValue mapping = new MappingJacksonValue(fullObjects);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
