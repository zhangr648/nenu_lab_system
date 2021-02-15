package cn.xiaoyh.service;

import cn.xiaoyh.pojo.People;

import java.util.List;

public interface PeopleService {

    boolean insertPeople(People people);

    People queryById(Long id);

    List<People> queryAll();
}
