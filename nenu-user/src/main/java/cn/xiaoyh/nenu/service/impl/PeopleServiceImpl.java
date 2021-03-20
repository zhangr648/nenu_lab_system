package cn.xiaoyh.nenu.service.impl;

import cn.xiaoyh.nenu.entity.People;
import cn.xiaoyh.nenu.mapper.PeopleMapper;
import cn.xiaoyh.nenu.service.PeopleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员 服务实现类
 * </p>
 *
 * @author xiaoyh
 * @since 2021-03-20
 */
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {

}
