# 데이터 베이스 생성
DROP DATABASE IF EXISTS lolHi;
CREATE DATABASE lolHi;
USE lolHi;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(200) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

# 회원
CREATE TABLE `member` (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id),
  regDate DATETIME NOT NULL,
  loginId CHAR(50) NOT NULL,
  loginPw CHAR(120) NOT NULL,
  `name` CHAR(50) NOT NULL
);

ALTER TABLE `lolHi`.`member` ADD UNIQUE INDEX (`loginId`); 

INSERT INTO `member`
SET regDate = NOW(),
`name` = '홍길동',
`loginId` = 'user1',
`loginPw` = 'user1';

INSERT INTO `member`
SET regDate = NOW(),
`name` = '홍길순',
`loginId` = 'user2',
`loginPw` = 'user2';