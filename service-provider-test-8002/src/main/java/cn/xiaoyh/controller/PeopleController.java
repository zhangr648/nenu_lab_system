package cn.xiaoyh.controller;

import cn.xiaoyh.dao.PeopleDao;
import cn.xiaoyh.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleController {

    private final PeopleDao peopleDao;

    public PeopleController(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @GetMapping("/people/{id}")
    public People getPeople(@PathVariable("id") Long id) {
        return peopleDao.queryById(id);
    }

    @GetMapping("/people/list")
    public List<People> queryAll() {
        return peopleDao.queryAll();
    }
}
