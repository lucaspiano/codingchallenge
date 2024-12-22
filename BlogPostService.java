package com.prosigliere.codingchallenge.service;

import com.prosigliere.codingchallenge.domain.dto.BlogPostDTO;
import com.prosigliere.codingchallenge.domain.model.BlogPost;
import com.prosigliere.codingchallenge.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    // Listar todos os posts com título e número de comentários
    public List<BlogPostDTO> getAllPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return blogPosts.stream().map(post -> {
            return new BlogPostDTO(post.getId(), post.getTitle(), null, null);
        }).collect(Collectors.toList());
    }

    // Criar um novo post
    public BlogPostDTO createPost(BlogPostDTO blogPostDTO) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(blogPostDTO.getTitle());
        newPost.setContent(blogPostDTO.getContent());

        BlogPost savedPost = blogPostRepository.save(newPost);
        return new BlogPostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), null);
    }

    // Recuperar um post específico por ID
    public Optional<BlogPostDTO> getPostById(Long id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        return blogPost.map(post -> new BlogPostDTO(post.getId(), post.getTitle(), post.getContent(), null));
    }
}
