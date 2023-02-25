create table report_forms
(
    id  bigint primary key auto_increment comment '序号',
    f1  varchar(500) null comment '部门',
    f2  varchar(500) null comment '培训对象',
    f3  varchar(500) null comment '培训标题',
    f4  varchar(500) null comment '培训类型',
    f5  varchar(500) null comment '培训费用(元)',
    f6  varchar(500) null comment '培训日期',
    f7  varchar(500) null comment '培训课时(h)',
    f8  varchar(500) null comment '培训讲师',
    f9  varchar(500) null comment '培训机构',
    f10 varchar(500) null comment '培训原因',
    f11 varchar(500) null comment '佐证资料',
    f12 varchar(500) null comment '上传附件',
    f13 varchar(500) null comment '其它说明'
) comment '培训报表';



