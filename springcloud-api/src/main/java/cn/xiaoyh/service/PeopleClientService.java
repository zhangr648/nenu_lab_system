package cn.xiaoyh.service;

import cn.xiaoyh.pojo.People;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "PROVIDER-TEST", fallbackFactory = PeopleServiceFallbackFactory.class)
public interface PeopleClientService {

    @PostMapping("/people/add")
    boolean insertPeople(People people);

    @GetMapping("/people/{id}")
    People queryById(@PathVariable("id") Long id);

    @GetMapping("people/list")
    List<People> queryAll();
}
