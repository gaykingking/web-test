package com.ls.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;


public class GeneratorConfig {
    public static void main(String[] args) {
        String finalProjectPath=System.getProperty("user.dir");
        FastAutoGenerator.create("", "", "")
                .globalConfig(builder -> {
                    builder.author("lingsheng") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() //禁止打开输出目录
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ls") // 设置父包名
                            //.moduleName("test") // 设置父包模块名
                            .entity("entity") //设置entity包名
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            //.other("dto") // 设置dto包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径

                }).strategyConfig(builder -> {
                    builder.addInclude(""); // 设置需要生成的表名
                            //.addTablePrefix("product_"); // 设置过滤表前缀
                }).templateEngine(new FreemarkerTemplateEngine()) .execute();
    }
}
