package jx.pgz.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class Gen {

    private static final String url = "jdbc:mysql://localhost:3306/xiaoedaikuan";
    private static final String username = "root";
    private static final String password = "123456";

    public static void main(String[] args) {

        Column createBy = new Column("create_by", FieldFill.INSERT);
        Column createTime = new Column("create_time", FieldFill.INSERT);


        FastAutoGenerator.create(url, username, password)
                .globalConfig((global) -> {
                    global.outputDir("C:\\Users\\pengmaofang\\Desktop\\music\\src\\main\\java")
                            .enableSwagger()
                            .author("admin")
                            .commentDate("yyyy")
                            .build();
                })
                .packageConfig((pck) -> {
                    pck.parent("jx.pgz.dao")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\pengmaofang\\Desktop\\music\\src\\main\\resources\\mapper"))
                            .build();
                })
                .strategyConfig(e -> {
                    e.addInclude("qna"
                                    ).build()
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .entityBuilder()
                            .enableChainModel()
                            .enableLombok()
                            .addTableFills(createBy, createTime).build();

                })
                .templateConfig(temp -> {

                    temp
                            .controller("/template/controller.java.vm")
                            .service("/template/service.java.vm")
                            .serviceImpl("/template/serviceImpl.java.vm")
                            .build();

                })

                .execute();


    }

}
