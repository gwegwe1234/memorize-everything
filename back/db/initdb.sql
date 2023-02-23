CREATE TABLE memorize.book
(
    id       int           NOT NULL AUTO_INCREMENT,
    title    varchar(50)   NOT NULL,
    summary  varchar(1000) NOT NULL,
    category varchar(50)   NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO memorize.book (id, title, summary, category) VALUES (null, '부의 시나리오', '기본적인 금리 정보 알려주는 책', '경제');