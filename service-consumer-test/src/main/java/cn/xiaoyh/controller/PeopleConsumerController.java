package cn.xiaoyh.controller;

import cn.xiaoyh.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PeopleConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PROVIDER_HOST = "http://localhost:8001";

    @GetMapping("/consumer/people/{id}")
    public People getPeople(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PROVIDER_HOST + "/people/" + id, People.class);
    }
}
