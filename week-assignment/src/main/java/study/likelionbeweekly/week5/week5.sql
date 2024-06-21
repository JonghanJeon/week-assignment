-- Comments 테이블의 외래 키 제약조건 삭제
ALTER TABLE Comments
    DROP FOREIGN KEY FK_Posts_TO_Comments_1;
ALTER TABLE Comments
    DROP FOREIGN KEY FK_Members_TO_Comments_1;

-- Posts 테이블의 외래 키 제약조건 삭제
ALTER TABLE Posts
    DROP FOREIGN KEY FK_Members_TO_Posts_1;

DROP TABLE IF EXISTS Members;
DROP TABLE IF EXISTS Posts;
DROP TABLE IF EXISTS Comments;

CREATE TABLE `Members`
(
    `MEMBER_ID` BIGINT       NOT NULL,
    `EMAIL`     VARCHAR(255) NULL,
    `NAME`      VARCHAR(255) NULL
);


CREATE TABLE `Posts`
(
    `POST_ID`     BIGINT       NOT NULL,
    `TITLE`       VARCHAR(255) NULL,
    `CONTENT`     VARCHAR(255) NULL,
    `MEMBER_ID`   BIGINT       NULL,
    `CREATE_TIME` TIMESTAMP    NULL,
    `MODIFY_TIME` TIMESTAMP    NULL
);


CREATE TABLE `Comments`
(
    `COMMENT_ID`  BIGINT       NOT NULL,
    `CONTENT`     VARCHAR(255) NULL,
    `CREATE_TIME` TIMESTAMP    NULL,
    `MODIFY_TIME` TIMESTAMP    NULL,
    `MEMBER_ID`       BIGINT       NULL,
    `POST_ID`     BIGINT       NULL
);

ALTER TABLE `Members`
    ADD CONSTRAINT `PK_MEMBERS` PRIMARY KEY (`MEMBER_ID`);

ALTER TABLE `Posts`
    ADD CONSTRAINT `PK_POSTS` PRIMARY KEY (`POST_ID`);

ALTER TABLE `Comments`
    ADD CONSTRAINT `PK_COMMENTS` PRIMARY KEY (`COMMENT_ID`);

ALTER TABLE `Posts`
    ADD CONSTRAINT `FK_Members_TO_Posts_1` FOREIGN KEY (`MEMBER_ID`)
        REFERENCES `Members` (`MEMBER_ID`);

ALTER TABLE `Comments`
    ADD CONSTRAINT `FK_Members_TO_Comments_1` FOREIGN KEY (`MEMBER_ID`)
        REFERENCES `Members` (`MEMBER_ID`);

ALTER TABLE `Comments`
    ADD CONSTRAINT `FK_Posts_TO_Comments_1` FOREIGN KEY (`POST_ID`)
        REFERENCES `Posts` (`POST_ID`);

/*# create
insert into Members(`MEMBER_ID`, `EMAIL`, `NAME`)
values (1, 'hong@naver.com', '홍길동');

# read
select *
from Members
where MEMBER_ID = 1;

# update
update Members
set EMAIL = 'kim@naver.com',
    NAME  = '김길동'
where MEMBER_ID = 1;

select *
from Members;

#delete
delete
from Members
where MEMBER_ID = 1;

select *
from Members;

#create
insert into Posts
values (1, '제목', '내용', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

#read
select *
from Posts
where POST_ID = 1;

#update
update Posts
set TITLE       = '첫번째 게시글',
    CONTENT     = '첫번째 게시글 본문입니다.',
    MODIFY_TIME = CURRENT_TIMESTAMP
where POST_ID = 1;

select *
from Posts;

#delete
delete from Posts where POST_ID = 1;

select *
from Posts;

#create
insert into Comments
values (1, '첫 번째 댓글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

#read
select * from Comments where COMMENT_ID = 1;

#update
update Comments
set CONTENT = '수정된 댓글 내용입니다.', MODIFY_TIME = CURRENT_TIMESTAMP
where COMMENT_ID = 1;

select *
from Comments;

#delete
delete from Comments where COMMENT_ID = 1;

select *
from Comments;*/


# 데이터 삽입
-- Members
INSERT INTO `Members` (`MEMBER_ID`, `EMAIL`, `NAME`)
VALUES (1, 'hong@naver.com', '홍길동'),
       (2, 'kim@naver.com', '김철수'),
       (3, 'lee@naver.com', '이영희');

-- Posts
INSERT INTO `Posts` (`POST_ID`, `TITLE`, `CONTENT`, `MEMBER_ID`, `CREATE_TIME`, `MODIFY_TIME`)
VALUES (1, '첫 번째 게시글', '첫 번째 게시글 내용입니다', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, '두 번째 게시글', '두 번째 게시글 내용입니다', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, '세 번째 게시글', '세 번재 게시글 내용입니다', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Comments
INSERT INTO `Comments` (`COMMENT_ID`, `CONTENT`, `CREATE_TIME`, `MODIFY_TIME`, `MEMBER_ID`, `POST_ID`)
VALUES (1, '첫 번째 댓글입니다', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
       (2, '두 번째 댓글입니다', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1);

# MEMBER_ID 를 기준으로 조인하여 게시글과 작성자 정보 조회
# INNER JOIN
SELECT *
FROM `Members` m
         INNER JOIN `Posts` p ON p.MEMBER_ID = m.MEMBER_ID;

# Members 테이블의 모든 회원과 그들이 작성한 게시글 정보를 조회.
# LEFT JOIN
SELECT *
FROM `Members` m
         LEFT JOIN `Posts` p ON m.MEMBER_ID = p.MEMBER_ID;

# Posts 테이블의 모든 게시글과 그 게시글을 작성한 회원 정보를 조회.
# RIGHT JOIN
SELECT *
FROM `Members` m
         RIGHT JOIN `Posts` p ON m.MEMBER_ID = p.MEMBER_ID;

# MySQL은 FULL JOIN 을 지원하지 않기 때문에 UNION(중복제거 합집합) 으로 FULL JOIN
# FULL JOIN
SELECT *
FROM `Members` m
         LEFT JOIN `Posts` p ON m.MEMBER_ID = p.MEMBER_ID
UNION
SELECT *
FROM `Members` m
         RIGHT JOIN `Posts` p ON m.MEMBER_ID = p.MEMBER_ID;