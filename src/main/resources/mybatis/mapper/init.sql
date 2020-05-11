create database college;
use college;

create table achievement
(
    id            int auto_increment
        primary key,
    user_id       int                not null comment '提交成果的用户id',
    title         varchar(150)       not null comment '项目标题/获奖名称',
    number        varchar(150)       null comment '项目编号/获奖题目',
    first_author  varchar(50)        not null comment '第一作者/完成人',
    department    varchar(50)        not null comment '所属单位',
    college       varchar(50)        not null comment '学校署名',
    subject       varchar(50)        null comment '一级学科',
    categories    varchar(50)        not null comment '学课门类',
    publish_type  varchar(50)        null comment '刊物/著作类型/获奖类别',
    publish_area  varchar(50)        null comment '出版地(著作)/发证机关',
    publish_time  datetime           null comment '发布/出版时间/获奖日期',
    publish_scope varchar(50)        null comment '发布/出版范围/单位/获奖级别',
    word_count    int                not null comment '字数/项目经费/万 /获奖人数',
    translation   tinyint(1)         null comment '是否译文',
    result        tinyint(1)         null comment '结题评价/是否合格',
    language      varchar(50)        null comment '语种(著作)',
    cn_issn       varchar(100)       null comment 'CN或ISSN号',
    isbn          varchar(100)       null comment 'ISBN号(著作)',
    comment       text               null comment '审核意见',
    review_time   datetime           null comment '审核日期',
    status        smallint default 0 not null comment '审核状态 0待审核 -1拒绝 1通过',
    reviewer      int                null comment '审核人id',
    type          varchar(20)        not null comment '论文 著作 项目结题 获奖'
)
    comment '成果表' collate = utf8mb4_bin;

create table announcement
(
    id           int auto_increment
        primary key,
    title        varchar(150) not null comment '公告标题',
    from_user_id int          not null comment '发送公告的人',
    to_user_id   int          null comment '接受公告的人 为空代表全部',
    content      text         not null comment '公告内容'
)
    comment '公告表' collate = utf8mb4_bin;

create table attachment
(
    id             int auto_increment
        primary key,
    achievement_id int          not null comment '成果标题',
    url            varchar(255) not null comment '附件地址',
    filename       varchar(255) not null comment '原文件名'
)
    comment '成果-附件表' collate = utf8mb4_bin;

create table author
(
    id             int auto_increment
        primary key,
    achievement_id int         not null comment '成果标题',
    author_name    varchar(50) not null comment '作者名称',
    gender         smallint    not null comment '作者名称',
    department     varchar(50) not null comment '所属单位',
    contribution   int         not null comment '贡献率',
    seq            int         not null comment '顺序',
    constraint author_seq_achievement_id_uindex
        unique (seq, achievement_id)
)
    comment '成果-作者表' collate = utf8mb4_bin;

create table course
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null comment '课程名',
    constraint course_name_uindex
        unique (name)
);

create table course_plan
(
    id         int auto_increment
        primary key,
    week       int not null comment '周几上课',
    section    int not null comment '第几节',
    alternate  int null comment '是否单双周 0否 1单周 2双周',
    start      int not null comment '开始周',
    end        int not null comment '结束周',
    course_id  int not null comment '课程id',
    teacher_id int not null comment '授课老师id',
    constraint teacher_uindex
        unique (week, section)
);

create table `group`
(
    id   int auto_increment
        primary key,
    name varchar(50) not null comment '组名',
    constraint group_name_uindex
        unique (name)
)
    comment '用户组表' collate = utf8mb4_bin;

create table group_role
(
    id       int auto_increment
        primary key,
    group_id int not null,
    role_id  int not null
)
    comment '组-角色关联表' collate = utf8mb4_bin;

create table questionnaire
(
    id          int auto_increment
        primary key,
    title       varchar(255)                       not null comment '问卷标题',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    end_time    date                               not null comment '截止时间',
    description varchar(255)                       null comment '描述',
    user_id     int                                not null comment '发起人id'
)
    comment '问卷调查' collate = utf8mb4_bin;

create table questionnaire_answer
(
    id          int auto_increment
        primary key,
    user_id     int                                not null comment '回答人id',
    item_id     int                                not null comment '问题id',
    answer      text                               not null comment '答案',
    create_time datetime default CURRENT_TIMESTAMP not null
)
    collate = utf8mb4_bin;

create table questionnaire_choice
(
    id      int auto_increment
        primary key,
    item_id int          not null comment '问题的id',
    text    varchar(255) null comment '选项文字'
)
    collate = utf8mb4_bin;

create table questionnaire_items
(
    id      int auto_increment
        primary key,
    title   text          null comment '问题',
    type    int           not null comment '问题类型
1 单选题
2 多选题
3 填空题
4 问答题',
    ques_id int default 0 not null
)
    comment '问卷调查题目' collate = utf8mb4_bin;

create table role
(
    id   int auto_increment
        primary key,
    name varchar(50) not null comment '角色名',
    constraint role_name_uindex
        unique (name)
)
    comment '角色表' collate = utf8mb4_bin;

create table source
(
    id          int auto_increment
        primary key,
    filename    varchar(255)                       not null comment '文件名',
    url         varchar(255)                       not null comment '地址',
    size        bigint                             not null comment '文件大小',
    type        varchar(255)                       not null comment '文件类型',
    user_id     int                                not null comment '用户id',
    create_time datetime default CURRENT_TIMESTAMP not null,
    download    int      default 0                 not null
)
    comment '教学资源';

create table user
(
    id        int auto_increment
        primary key,
    name      varchar(50)            not null comment '教师姓名',
    gender    smallint               null comment '性别 null/0其他 1男 2女',
    age       smallint               not null comment '年龄',
    telephone varchar(11) default '' not null comment '手机号',
    password  varchar(50) default '' not null comment '密码',
    constraint user_telephone_uindex
        unique (telephone)
)
    comment '用户表' collate = utf8mb4_bin;

create table user_group
(
    id       int auto_increment
        primary key,
    user_id  int not null,
    group_id int not null,
    constraint user_group_group_id_user_id_uindex
        unique (group_id, user_id)
)
    comment '用户-组关联表' collate = utf8mb4_bin;

