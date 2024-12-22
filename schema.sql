CREATE TABLE blog_post (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           content TEXT
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content TEXT NOT NULL,
                         author VARCHAR(255),
                         blog_post_id BIGINT NOT NULL,
                         CONSTRAINT fk_blog_post FOREIGN KEY (blog_post_id) REFERENCES blog_post(id)
);
