package jx.pgz.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


public class CodeGenerator {


    /**
     * 读取控制台内容
     */

    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/basic?characterEncoding=UTF-8";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String username ="root";
        String password = "123456";
        String[] table = new String[]{"sys_authority","sys_file" ,"sys_role" ,"sys_user"};




        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("C:\\Users\\pengmaofang\\Desktop\\" + "music/src/main/java");
        gc.setAuthor("");
        gc.setOpen(false);
        gc.setSwagger2(false);
        gc.setFileOverride(true);
        gc.setIdType(IdType.AUTO);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("sys");
        pc.setParent("jx.pgz.dao");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

          strategy.setInclude(table);

//        strategy.setTablePrefix("p_");


        strategy.setLogicDeleteFieldName("del_flag");

        List<TableFill> tableFills=new ArrayList<>();
        tableFills.add(new TableFill("create_by", FieldFill.INSERT));
        tableFills.add(new TableFill("update_by", FieldFill.UPDATE));
        tableFills.add(new TableFill("create_time", FieldFill.INSERT));
        tableFills.add(new TableFill("update_time", FieldFill.UPDATE));

        strategy.setTableFillList(tableFills);


        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
//        templateConfig.setController(null);
        mpg.setTemplate(templateConfig);

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }



}
