package cn.xiaoyh.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XTest {

    @Test
    public void test01() {
        List<Object> list = new ArrayList<Object>() {{
           add(null);
           add("123");
        }};

        for (Object object : list) {
            System.out.println(object);
        }
    }
}
