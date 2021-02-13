package cn.xiaoyh.controller;

import cn.xiaoyh.pojo.People;
import cn.xiaoyh.service.PeopleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/people/{id}")
    public People getPeople(@PathVariable("id") Long id) {
        return peopleService.queryById(id);
    }

    @GetMapping("/people/list")
    public List<People> queryAll() {
        return peopleService.queryAll();
    }
}
