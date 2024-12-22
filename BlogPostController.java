package com.prosigliere.codingchallenge.controller;

import com.prosigliere.codingchallenge.domain.dto.BlogPostDTO;
import com.prosigliere.codingchallenge.domain.model.BlogPost;
import com.prosigliere.codingchallenge.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostController(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    // Endpoint para listar todos os posts com título e número de comentários
    @GetMapping
    public List<BlogPostDTO> getAllPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return blogPosts.stream().map(post -> {
            // No momento, apenas o título é retornado, mas pode incluir o número de comentários
            return new BlogPostDTO(post.getId(), post.getTitle(), null, null);
        }).collect(Collectors.toList());
    }

    // Endpoint para criar um novo blog post
    @PostMapping
    public ResponseEntity<BlogPostDTO> createPost(@RequestBody BlogPostDTO blogPostDTO) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(blogPostDTO.getTitle());
        newPost.setContent(blogPostDTO.getContent());

        BlogPost savedPost = blogPostRepository.save(newPost);
        BlogPostDTO responseDTO = new BlogPostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), null);

        return ResponseEntity.status(201).body(responseDTO);
    }

    // Endpoint para recuperar um post específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> getPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    BlogPostDTO responseDTO = new BlogPostDTO(post.getId(), post.getTitle(), post.getContent(), null);
                    return ResponseEntity.ok(responseDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
