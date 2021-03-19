INSERT INTO library(id) VALUES (null);
INSERT INTO library(id) VALUES (null);
INSERT INTO library(id) VALUES (null);
INSERT INTO library(id) VALUES (null);

INSERT INTO book(id, title, author, library_id) VALUES(null, '책제목1', '작가1', 1L);
INSERT INTO book(id, title, author, library_id) VALUES(null, '책제목2', '작가2', 1L);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목3', '작가3', 1L, true);
INSERT INTO book(id, title, author, library_id) VALUES(null, '책제목4', '작가4', 2L);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목4', '작가4', 2L, false);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목4', '작가4', 2L, true);
INSERT INTO book(id, title, author, library_id, is_borrowed) VALUES(null, '책제목4', '작가4', 2L, true);


INSERT INTO member(id, password, name) VALUES('id1', 'pwd1', 'name1');
INSERT INTO member(id, password, name) VALUES('id2', 'pwd2', 'name2');
INSERT INTO member(id, password, name) VALUES('id3', 'pwd3', 'name3');

INSERT INTO admin(id, password, name) VALUES('id4', 'pwd4', 'name4');
INSERT INTO admin(id, password, name) VALUES('id5', 'pwd5', 'name5');


INSERT INTO borrowed_book(id, book_id, user_id, b_date) VALUES(null, 1L, 'id1', null);
INSERT INTO borrowed_book(id, book_id, user_id, b_date) VALUES(null, 2L, 'id1', null);
INSERT INTO borrowed_book(id, book_id, user_id, b_date) VALUES(null, 4L, 'id1', null);
