package com.prosigliere.codingchallenge.repository;

import com.prosigliere.codingchallenge.domain.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
