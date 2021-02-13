package cn.xiaoyh.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class People implements Serializable {
    private Long id;
    private String name;
    private int age;
    private String phone;
}
