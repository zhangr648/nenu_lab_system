package cn.xiaoyh.service;

import cn.xiaoyh.pojo.People;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleServiceFallbackFactory implements FallbackFactory<PeopleClientService> {
    public PeopleClientService create(Throwable throwable) {
        return new PeopleClientService() {
            public boolean insertPeople(People people) {
                return false;
            }

            public People queryById(Long id) {
                return new People().setId(id).setName("该服务已关闭");
            }

            public List<People> queryAll() {
                return null;
            }
        };
    }
}
