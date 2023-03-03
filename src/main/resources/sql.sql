create table car
(
    id   bigint primary key auto_increment comment '汽车编号',
    name varchar(64) comment '汽车名称'
) comment '汽车表';


create table report
(
    id bigint primary key auto_increment comment 'ID',
    car_id bigint comment '汽车ID',
    par_id bigint comment '零件ID',
    pass  varchar(5) comment '是否合格',
    check_time datetime comment '检测时间'
) comment '检测表';


create table car_part
(
    id              bigint primary key auto_increment comment 'ID',
    car_id          bigint comment '汽车编号',
    part_id         bigint comment '零件编号',
    actual_quantity int comment '实际数量'
) comment '汽车零件关联表';

create table part
(
    id   bigint primary key auto_increment comment '零件编号',
    name varchar(64) comment '零件名称',
    num  int comment '库存'
) comment '零件表';

create table transfer_order
(
    part_id bigint comment '零件编号',
    num     int comment '调拨数量',
    car_id  bigint comment '汽车编号'
) comment '调拨单';