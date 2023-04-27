-- create schema if not exists `enjoy_trip` default character set utf8mb4 collate utf8mb4_0900_ai_ci;

use goatdb;

drop table if exists member;

create table if not exists `MEMBER`
(
    `member_id`                   bigint      not null primary key auto_increment,
    `login_id`                    varchar(20) not null,
    `login_pw`                    varchar(20) not null,
    `username`                    varchar(20) not null,
    `email`                       varchar(50) not null,
    `phone`                       varchar(11) not null,
    `birth`                       varchar(6)  not null,
    `gender`                      varchar(1)  not null,
    `nickname`                    varchar(10) not null,
    `nickname_last_modified_date` timestamp   not null default current_timestamp,
    `authority`                   varchar(10) not null default 'client',
    `created_date`                timestamp   not null default current_timestamp,
    `last_modified_date`          timestamp   not null default current_timestamp
    );

drop table if exists content_type;

create table if not exists `CONTENT_TYPE`
(
    `content_type_id`   int         not null primary key,
    `content_type_name` varchar(10) not null
    );

create table if not exists `ARTICLE`
(
    `article_id`         bigint        not null primary key auto_increment,
    `member_id`          bigint        not null,
    `title`              varchar(100)  not null,
    `content`            varchar(1000) not null,
    `hit`                int           not null default 0,
    `created_date`       timestamp     not null default current_timestamp,
    `last_modified_date` timestamp     not null default current_timestamp,
    foreign key (`member_id`) references MEMBER (`member_id`)
    );

create table if not exists `NOTION`
(
    `notion_id`          bigint        not null primary key auto_increment,
    `title`              varchar(100)  not null,
    `content`            varchar(1000) not null,
    `hit`                int           not null default 0,
    `top`                boolean       not null default false,
    `created_by`          bigint        not null,
    `last_modified_by`   bigint        not null,
    `created_date`       timestamp     not null default current_timestamp,
    `last_modified_date` timestamp     not null default current_timestamp,
    foreign key (`created_by`) references MEMBER (`member_id`),
    foreign key (`last_modified_by`) references MEMBER (`member_id`)
    );

create table if not exists `HOT_PLACE`
(
    `hot_place_id` bigint not null primary key auto_increment,
    `name` varchar(50) not null,
    `desc` varchar(500) not null,
    `hit` int not null default 0,
    `vote` int not null default 0,
    `visited_date` varchar(10) not null,
    `upload_file_name` varchar(255) not null,
    `store_file_name` varchar(255) not null,
    `member_id` bigint not null,
    `content_id` int not null,
    `content_type_id` int not null,
    `created_date` timestamp not null default current_timestamp,
    `last_modified_date` timestamp not null default current_timestamp,
    foreign key (`member_id`) references MEMBER (`member_id`),
    foreign key (`content_id`) references ATTRACTION_INFO (`content_id`)
    );

create table if not exists `FAVORITE`
(
    `favorite_id` bigint not null primary key auto_increment,
    `member_id` bigint not null,
    `hotplace_id` bigint not null,
    foreign key (`member_id`) references MEMBER (`member_id`),
    foreign key (`hotplace_id`) references HOT_PLACE (`hot_place_id`)
    );



create table if not exists `trip_plan`
(
    `trip_plan_id` bigint not null primary key auto_increment,
    `member_id` bigint not null,
    `title` varchar(100) not null,
    `created_date` timestamp not null default current_timestamp,
    `last_modified_date` timestamp not null default current_timestamp,
    foreign key (`member_id`) references MEMBER (`member_id`)
    );

create table if not exists `detail_plan`
(
    `detail_plan_id` bigint not null primary key auto_increment,
    `trip_plan_id` bigint not null,
    `content_id` int not null,
    `sequence` int not null default 0,
    `created_date` timestamp not null default current_timestamp,
    `last_modified_date` timestamp not null default current_timestamp,
    foreign key (`trip_plan_id`) references TRIP_PLAN (`trip_plan_id`),
    foreign key (`content_id`) references ATTRACTION_INFO (`content_id`)
    );

create table if not exists `TREND`
(
    `trend_id` bigint not null primary key auto_increment,
    `content_id` int not null,
    `teenage` int not null,
    `twenty` int not null,
    `thirty` int not null,
    `male` int not null,
    `female` int not null,
    foreign key (`content_id`) references ATTRACTION_INFO (`content_id`)
    )