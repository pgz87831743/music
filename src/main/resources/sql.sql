create table basic_service.position
(
    id           bigint auto_increment
        primary key,
    address      varchar(255) null,
    x            double       null,
    y            double       null,
    z            double       null,
    stay         int          null,
    timestamp    bigint       null,
    bs_address   bigint       null,
    sample_time  varchar(255) null,
    sample_batch int          null
);

create table basic_service.running
(
    id                      bigint auto_increment
        primary key,
    address                 varchar(256) null,
    power                   varchar(256) null,
    heart_rate              varchar(256) null,
    step                    bigint       null,
    temperature             varchar(256) null,
    systolic_pressure       varchar(256) null,
    diastolic_pressure      varchar(256) null,
    accx                    bigint       null,
    accy                    bigint       null,
    gyroscopex              bigint       null,
    gyroscopey              bigint       null,
    gyroscopez              bigint       null,
    stay                    bigint       null,
    sos                     varchar(256) null,
    elock_open_illegal_warn varchar(256) null,
    timestamp               bigint       null,
    bs_address              varchar(256) null,
    sample_time             varchar(256) null,
    sample_batch            bigint       null
);

create table basic_service.sys_file
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(64)   not null comment '文件名称',
    md5         varchar(64)   not null comment 'md5',
    path        varchar(1000) not null comment '文件路径',
    create_time datetime      null,
    update_time datetime      null,
    update_by   bigint        null,
    create_by   bigint        null
);

