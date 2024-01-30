package cn.online.pay.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = {"cn.online.pay.service.mapper"})
@Configuration
public class MybatisConfig {
}
