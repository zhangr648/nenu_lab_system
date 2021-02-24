package cn.xiaoyh.test;

import cn.xiaoyh.pojo.People;
import org.junit.Test;

public class PeopleTest {

    @Test
    public void test01() {
        People people = new People();
        people.setAge(21).setName("123");
        System.out.println(1);
    }
}
