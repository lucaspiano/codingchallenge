-- Inserindo dados iniciais para blog_post
INSERT INTO blog_post (title, content) VALUES
                                           ('First Post', 'This is the content of the first post.'),
                                           ('Second Post', 'This is the content of the second post.');

-- Inserindo dados iniciais para comment
INSERT INTO comment (content, author, blog_post_id) VALUES
                                                        ('Great post!', 'Alice', 1),
                                                        ('I learned a lot!', 'Bob', 1),
                                                        ('Thanks for sharing.', 'Charlie', 2);
