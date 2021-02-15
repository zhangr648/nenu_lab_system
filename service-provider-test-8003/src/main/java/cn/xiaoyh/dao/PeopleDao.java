package cn.xiaoyh.dao;

import cn.xiaoyh.pojo.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleDao {

    public boolean insertPeople(People people) {
        return true;
    }

    public People queryById(Long id) {
        return new People().setId(id).setName("第三个提供者");
    }

    public List<People> queryAll() {
        return new ArrayList<People>() {{
            for (int i = 0; i < 5; i++) {
                add(new People());
            }
        }};
    }
}
