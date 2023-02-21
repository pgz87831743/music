create table basic_service.daily_record
(
    id          bigint auto_increment
        primary key,
    pet_id      bigint      not null comment '宠物ID',
    wsl         float       null comment '喂食量（按体重计算推荐喂食量，公式等我回去就发您）',
    ysl         float       null comment '饮水量（推荐饮水量为体重的千克数乘六十，单位为毫升）',
    sy          varchar(10) null comment '刷牙（是或否）',
    pb          varchar(10) null comment '排便（是否正常）',
    tz          float       null comment '体重（今日是否变化，如有变化为多少）',
    hdl         varchar(10) null comment '活动量（大，中，小）',
    qc          varchar(10) null comment '驱虫（是或否，如果距离上次驱虫时间小于一个月则发出驱虫频繁预警）',
    create_by   bigint      null comment '创建人',
    create_time datetime    null comment '创建时间'
);

create table basic_service.feeding_skills
(
    id          bigint auto_increment comment '主键'
        primary key,
    xm          varchar(255) null comment '姓名',
    zl          varchar(255) null comment '种类',
    ys          varchar(500) null comment '饮食',
    xw          varchar(500) null comment '行为',
    cwyp        varchar(500) null comment '宠物用品',
    create_by   bigint       null comment '创建人',
    create_time datetime     null comment '创建时间'
);

create table basic_service.health_monitoring
(
    id          bigint auto_increment
        primary key,
    pet_id      bigint       not null comment '宠物ID',
    type        varchar(64)  not null comment '监测类型',
    recommend   varchar(255) not null comment '参考策略',
    create_by   bigint       null comment '创建人',
    create_time datetime     null comment '创建时间'
);

create table basic_service.pet_file
(
    id          bigint auto_increment
        primary key,
    xm          varchar(255) null comment '姓名',
    zl          varchar(255) null comment '种类（猫或狗就可以）',
    nl          float        null comment '年龄',
    qz          float        null comment '体重（kg）',
    jy          varchar(255) null comment '绝育情况（是否）',
    ymqk        varchar(64)  null comment '疫苗情况（未免疫，未完全免疫，已完全免疫）',
    gwbs        varchar(255) null comment '过往病史（填写文字）',
    sex         varchar(10)  null comment '性别',
    sc          float        null comment '身长',
    create_by   bigint       null comment '创建人',
    create_time datetime     null comment '创建时间'
);

create table basic_service.sys_user
(
    id          bigint auto_increment
        primary key,
    username    varchar(128) not null,
    password    varchar(128) not null,
    salt        varchar(128) null,
    create_time datetime     null,
    update_time datetime     null,
    update_by   bigint       null,
    create_by   bigint       null,
    constraint sys_user_pk
        unique (username)
);

