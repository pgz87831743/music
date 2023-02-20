package jx.pgz.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import jx.pgz.utils.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;


@Configuration
@MapperScan({"jx.pgz.dao.*.mapper"})
@EnableTransactionManagement
public class MyBatisPlusConfiguration implements  MetaObjectHandler {


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        if (UserContext.getInstance()!=null){
            this.strictInsertFill(metaObject, "createBy", () -> UserContext.getInstance().getUserId(), Long.class); // 起始版本 3.3.3(推荐)
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {


    }
}
