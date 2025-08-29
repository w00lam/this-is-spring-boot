INSERT INTO member(name, email, password) VALUES('구덕팔','Duck89@naver.com','$2a$12$uS8zx39IxS6tYo4UPku6w.hrGnlLJMRXWCd4HXQFN9RHy8HrIGuja');

INSERT INTO member(name, email, password) VALUES('팔덕구','Duck98@naver.com','$2a$12$uS8zx39IxS6tYo4UPku6w.hrGnlLJMRXWCd4HXQFN9RHy8HrIGuja');

INSERT INTO member(name, email, password) VALUES('김갑수','GapsooKim@naver.com','$2a$12$uS8zx39IxS6tYo4UPku6w.hrGnlLJMRXWCd4HXQFN9RHy8HrIGuja');

INSERT INTO member(name, email, password) VALUES('팔갑수','GapsooPal@naver.com','$2a$12$uS8zx39IxS6tYo4UPku6w.hrGnlLJMRXWCd4HXQFN9RHy8HrIGuja');

INSERT INTO authority(authority, member_id) VALUES('ROLE_ADMIN', 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('첫 번째 게시글 제목', '첫 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('두 번째 게시글 제목', '두 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('세 번째 게시글 제목', '세 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

INSERT INTO article(title, description, created, updated, member_id)
VALUES('네 번째 게시글 제목', '네 번째 게시글 본문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);