INSERT INTO member(name, email, password) VALUES('구덕팔','Duck89@naver.com','$2a$12$34n2GHTdh8ecPa/d.mAV.usG4u5nFQ86Tj6rPaZkjXVz5PoipvymS');

INSERT INTO member(name, email, password) VALUES('팔덕구','Duck98@naver.com','$2a$12$34n2GHTdh8ecPa/d.mAV.usG4u5nFQ86Tj6rPaZkjXVz5PoipvymS');

INSERT INTO member(name, email, password) VALUES('김갑수','GapsooKim@naver.com','$2a$12$34n2GHTdh8ecPa/d.mAV.usG4u5nFQ86Tj6rPaZkjXVz5PoipvymS');

INSERT INTO member(name, email, password) VALUES('팔갑수','GapsooPal@naver.com','$2a$12$34n2GHTdh8ecPa/d.mAV.usG4u5nFQ86Tj6rPaZkjXVz5PoipvymS');

INSERT INTO authority(authority, member_id) VALUES('ROLE_ADMIN', 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('첫 번째 게시글 제목', '첫 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('두 번째 게시글 제목', '두 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('세 번째 게시글 제목', '세 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('네 번째 게시글 제목', '네 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('다섯 번째 게시글 제목', '다섯 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('여섯 번째 게시글 제목', '여섯 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('일곱 번째 게시글 제목', '일곱 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열덟 번째 게시글 제목', '열덟 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('아홉 번째 게시글 제목', '아홉 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열 번째 게시글 제목', '열 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열한 번째 게시글 제목', '열한 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열두 번째 게시글 제목', '열두 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열세 번째 게시글 제목', '열세 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열네 번째 게시글 제목', '열네 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열다섯 번째 게시글 제목', '열다섯 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('열여섯 번째 게시글 제목', '열여섯 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);