package cn.xiaoyh.controller;

import cn.xiaoyh.pojo.People;
import cn.xiaoyh.service.PeopleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="execution.timeout.enabled", value="true"),
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
            },
            fallbackMethod = "hystrixGetPeople"
    )
    public People getPeople(@PathVariable("id") Long id) {
        if (id.equals(123L)) throw new RuntimeException("该用户不存在");

        return peopleService.queryById(id);
    }

    @GetMapping("/people/list")
    public List<People> queryAll() {
        return peopleService.queryAll();
    }

    public People hystrixGetPeople(@PathVariable("id") Long id) {
        return new People().setId(id).setName("没找到该用户");
    }
}
