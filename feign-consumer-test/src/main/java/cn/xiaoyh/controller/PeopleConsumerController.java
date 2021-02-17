package cn.xiaoyh.controller;

import cn.xiaoyh.pojo.People;
import cn.xiaoyh.service.PeopleClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleConsumerController {

    @Autowired
    private PeopleClientService service;

    @GetMapping("/people/{id}")
    public People getPeople(@PathVariable("id") Long id) {
        return service.queryById(id);
    }

    @GetMapping("/people/list")
    public List<People> getAllPeople() {
        return service.queryAll();
    }
}
