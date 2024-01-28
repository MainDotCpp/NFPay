package nf.online.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://leuan.top:5432/nf_pay?useUnicode=true&characterEncoding=utf-8&useSSL=false", "yy", "yy")
                .globalConfig(builder -> {
                    builder.author("yang yang")
                            .disableOpenDir()
                            .outputDir("/Users/yangyang/Projects/NFPay/service/src/main/java");
                }).packageConfig(builder -> {
                    builder.parent("nf.online.pay")
                            .moduleName("service") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/yangyang/Projects/NFPay/service/src/main/resources/mappers"));
                }).strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok()
                            .enableFileOverride();
                    builder.addInclude("t_mach_info","t_mch_info")
                            .addTablePrefix("t_");
                }).templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
