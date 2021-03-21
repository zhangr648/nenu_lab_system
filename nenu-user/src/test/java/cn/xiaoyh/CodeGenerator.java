package cn.xiaoyh;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CodeGenerator {

    @Value("${spring.datasource.driver-class-name}")
    private String driveName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Test
    public void run() {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor("xiaoyh");
        gc.setFileOverride(true);           // 是否覆盖已有文件
        gc.setOpen(false);                  // 生成后是否打开资源管理器
        gc.setServiceName("%sService");	    // 去掉Service接口的首字母I
        gc.setIdType(IdType.ASSIGN_ID);     // 主键策略
        gc.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(driveName);
        dsc.setUrl(url);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.xiaoyh");
        pc.setModuleName("nenu");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 设置数据库表名，不写为所有表都生成
        //strategy.setInclude("nenu_user", "nenu_role");
        strategy.setNaming(NamingStrategy.underline_to_camel);  // 数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_");      // 生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    //数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true);    // lombok 模型
        strategy.setChainModel(true);           // @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true);          // Restful api 风格控制器
        strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
