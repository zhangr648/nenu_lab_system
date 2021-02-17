package cn.xiaoyh.dao;

import cn.xiaoyh.pojo.People;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PeopleDao {

    @Insert("insert into nenu_people (age, name, phone) values (#{age}, #{name}, #{phone})")
    boolean insertPeople(People people);

    @Select("select * from nenu_people where id=#{id}")
    People queryById(Long id);

    @Select("select * from nenu_people")
    List<People> queryAll();
}
