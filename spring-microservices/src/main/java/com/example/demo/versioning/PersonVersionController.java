package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController
{
    @GetMapping(path = "v1/person")
    public PersonV1 getPersonV1()
    {
        return new PersonV1("Tanvi Agrawal");
    }

    @GetMapping("v2/person")
    public PersonV2 getPersonV2()
    {
        return new PersonV2(new Name("Tanvi", "Agrawal"));
    }

//    doing versioning through request params = http://localhost:8080/person?version=1
    @GetMapping(value = "person", params = "version=1")
    public PersonV1 getPersonV1ThroughParams()
    {
        return new PersonV1("Tanvi Agrawal");
    }

//    http://localhost:8080/person?version=2
    @GetMapping(value = "person", params = "version=2")
    public PersonV2 getPersonV2ThroughParams()
    {
        return new PersonV2(new Name("Tanvi", "Agrawal"));
    }
}
