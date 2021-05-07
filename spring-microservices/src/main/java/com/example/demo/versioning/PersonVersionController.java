package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController
{
    /*
    Factors to consider =
    1. URI polluting - in uri versioning and param versioning, we pollute the URI space
    2. Misuse of Http Headers - they were never meant for versioning, like in content negotiation and header versioning
    3. caching - we cannot cache uri and param versioning because request body has changed.
    4. users cannot execute header related versioning on browsers without additional plugins.
    5. think about documentation of the API (difficult with headers)
    but these are used everywhere. there is no perfect solution. but do finalize the versioning before developing.
     */
//  1.  URI VERSIONING
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

//  2.   doing versioning through request params = http://localhost:8080/person?version=1
//    REQUEST PARAM VERSIONING
    @GetMapping(value = "person/params", params = "version=1")
    public PersonV1 getPersonV1ThroughParams()
    {
        return new PersonV1("Tanvi Agrawal");
    }

//    http://localhost:8080/person?version=2
    @GetMapping(value = "person/params", params = "version=2")
    public PersonV2 getPersonV2ThroughParams()
    {
        return new PersonV2(new Name("Tanvi", "Agrawal"));
    }

//  3.   versioning through headers - HEADER VERSIONING -  send through X-API_VERSION header in the request
    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1ThroughHeaders()
    {
        return new PersonV1("Tanvi Agrawal");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2ThroughHeaders()
    {
        return new PersonV2(new Name("Tanvi", "Agrawal"));
    }

//  4.  versioning through produces - CONTENT NEGOTIATION, MIME TYPE VERSIONING - send through Accept header
    @GetMapping(value = "person/produces", produces = "application/vnd.tanvi.app-v1+json")
    public PersonV1 getPersonV1ThroughContentNegotiation()
    {
        return new PersonV1("Tanvi Agrawal");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.tanvi.app-v2+json")
    public PersonV2 getPersonV2ThroughContentNegotiation()
    {
        return new PersonV2(new Name("Tanvi", "Agrawal"));
    }
}
