package cn.xiaoyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xiaoyh.nenu.mapper")
public class NenuUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(NenuUserApplication.class, args);
    }
}
