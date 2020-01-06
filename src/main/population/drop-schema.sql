
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `audit_record` 
       drop 
       foreign key `FKdcrrgv6rkfw2ruvdja56un4ji`;

    alter table `audit_record` 
       drop 
       foreign key `FKlbvbyimxf6pxvbhkdd4vfhlnd`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `auditor_request` 
       drop 
       foreign key `FKe7pjjdlehi2gl4wqda0druv4g`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `commercial_banner` 
       drop 
       foreign key `FKfp0yot74q1m8ofbclq3nlfidw`;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `duty` 
       drop 
       foreign key `FKs2uoxh4i5ya8ptyefae60iao1`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `message` 
       drop 
       foreign key `FK3ny0h1379q528toyokq81noiu`;

    alter table `message` 
       drop 
       foreign key `FK28hjkn063wrsjuiyyf8sm3s2v`;

    alter table `participation` 
       drop 
       foreign key `FKd49qqi98v3aa6v9v5atuinodj`;

    alter table `participation` 
       drop 
       foreign key `FKk6j425rhm4ahsi6cf2bg2um4l`;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `sponsor_commercial_banner` 
       drop 
       foreign key `FKhkuqo775kcnn6dhfx2rom9vxe`;

    alter table `sponsor_commercial_banner` 
       drop 
       foreign key `FKqac66ts1tvlks9udr9wii6v36`;

    alter table `sponsor_credit_card` 
       drop 
       foreign key `FKt0sa58b5v36v0jsrw0vnq3g00`;

    alter table `sponsor_credit_card` 
       drop 
       foreign key `FKiuxseh035tds7d7nhn2eq4yp`;

    alter table `sponsor_non_commercial_banner` 
       drop 
       foreign key `FKyd2w2fypnhvu2rqdx8gvef6k`;

    alter table `sponsor_non_commercial_banner` 
       drop 
       foreign key `FKfk1oyb4q1ptp7ggp317y7o30p`;

    alter table `thread` 
       drop 
       foreign key `FKkoj53cnb5t2fhfm33gb9bvff1`;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit_record`;

    drop table if exists `auditor`;

    drop table if exists `auditor_request`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `challenge`;

    drop table if exists `commercial_banner`;

    drop table if exists `company_record`;

    drop table if exists `consumer`;

    drop table if exists `credit_card`;

    drop table if exists `customparams`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `investor`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `non_commercial_banner`;

    drop table if exists `offer`;

    drop table if exists `participation`;

    drop table if exists `provider`;

    drop table if exists `request`;

    drop table if exists `sponsor`;

    drop table if exists `sponsor_commercial_banner`;

    drop table if exists `sponsor_credit_card`;

    drop table if exists `sponsor_non_commercial_banner`;

    drop table if exists `thread`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `hibernate_sequence`;
