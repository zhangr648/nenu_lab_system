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

    private static final String PROVIDER_NAME = "SPRINGCLOUD-PROVIDER-TEST";

    @GetMapping("/consumer/people/{id}")
    public People getPeople(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://" + PROVIDER_NAME + "/people/" + id, People.class);
    }
}
