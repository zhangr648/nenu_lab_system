package cn.xiaoyh.service;

import cn.xiaoyh.dao.PeopleDao;
import cn.xiaoyh.pojo.People;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService{

    private final PeopleDao peopleDao = new PeopleDao();

    public boolean insertPeople(People people) {
        return peopleDao.insertPeople(people);
    }

    public People queryById(Long id) {
        return peopleDao.queryById(id);
    }

    public List<People> queryAll() {
        return peopleDao.queryAll();
    }
}
