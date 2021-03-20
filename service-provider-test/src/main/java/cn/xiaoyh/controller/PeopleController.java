package cn.xiaoyh.controller;

import cn.xiaoyh.dao.PeopleDao;
import cn.xiaoyh.pojo.People;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="execution.timeout.enabled", value="true"),
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000"),
            },
            fallbackMethod = "hystrixGetPeople"
    )
    public People getPeople(@PathVariable("id") Long id) {
        return peopleDao.queryById(id);
    }

    @GetMapping("/people/list")
    public List<People> queryAll() {
        return peopleDao.queryAll();
    }

    public People hystrixGetPeople(@PathVariable("id") Long id) {
        return new People().setId(id).setName("没找到该用户");
    }
}
