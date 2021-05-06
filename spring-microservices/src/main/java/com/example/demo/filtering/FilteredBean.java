package com.example.demo.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

//@JsonIgnoreProperties(value = {"field2"}) -> static filtering = never return that field. (ex : Password)
@JsonFilter("someBeanFilter")
public class FilteredBean
{
    private String field1;

//    this is some sensitive field - dont want to display in GET results
//    this does not show up in the Schema of FilteredBean in the Swagger documentation too.
    private String field2;

    private String field3;

    public FilteredBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "FilteredBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
