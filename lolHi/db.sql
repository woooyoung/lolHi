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

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(100) NOT NULL
);

# 회원 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '홍길동';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '홍길순';

# 게시물 테이블에 memberId 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;
# 기존 게시물들의 작성자는 1번 회원으로 정한다.
UPDATE article SET memberId = 1 WHERE memberId = 0;

# 게시물 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 2;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목4',
`body` = '내용4',
memberId = 2;

# 댓글 테이블 생성
CREATE TABLE reply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relId INT(10) UNSIGNED NOT NULL, # 관련 데이터 ID
    relTypeCode CHAR(50) NOT NULL # 관련 데이터 타입
);

# 댓글 데이터 추가
INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
`body` = '댓글 1',
memberId = 1;

INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
`body` = '댓글 2',
memberId = 1;

INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
`body` = '댓글 3',
memberId = 1;

INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
`body` = '댓글 4',
memberId = 2;

# 게시판 테이블 추가
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(20) UNIQUE NOT NULL,
    `code` CHAR(20) UNIQUE NOT NULL
);

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = '공지사항',
`code` = 'notice';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = '자유',
`code` = 'free';

# 게시물 테이블에 boardId 칼럼 추가
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER updateDate;
UPDATE article SET boardId = 1 WHERE id <= 2;
UPDATE article SET boardId = 2 WHERE id > 2;

# 회원테이블에 email 칼럼 추가
ALTER TABLE MEMBER ADD COLUMN email CHAR(100) NOT NULL NOT NULL AFTER `name`;
UPDATE `member` SET email = 'axdsw121@gmail.com' WHERE email = '';

# 부가정보테이블 
# 댓글 테이블 추가
CREATE TABLE attr (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `relTypeCode` CHAR(20) NOT NULL,
    `relId` INT(10) UNSIGNED NOT NULL,
    `typeCode` CHAR(30) NOT NULL,
    `type2Code` CHAR(30) NOT NULL,
    `value` TEXT NOT NULL
);

# attr 유니크 인덱스 걸기
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`); 

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`);

# attr에 만료날짜 추가
ALTER TABLE `attr` ADD COLUMN `expireDate` DATETIME NULL AFTER `value`;

# 기존 패스워드 암호화
UPDATE `member`
SET loginPw = SHA2(loginPw, 256);
