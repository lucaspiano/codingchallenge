package com.prosigliere.codingchallenge.controller;

import com.prosigliere.codingchallenge.domain.dto.CommentDTO;
import com.prosigliere.codingchallenge.domain.model.Comment;
import com.prosigliere.codingchallenge.domain.model.BlogPost;
import com.prosigliere.codingchallenge.repository.BlogPostRepository;
import com.prosigliere.codingchallenge.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;

    // Injeção de dependência via construtor
    public CommentController(BlogPostRepository blogPostRepository, CommentRepository commentRepository) {
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }

    // Endpoint para adicionar um comentário a um post específico
    @PostMapping
    public ResponseEntity<CommentDTO> addCommentToPost(@PathVariable Long postId, @RequestBody CommentDTO commentDTO) {
        return blogPostRepository.findById(postId)
                .map(post -> {
                    Comment newComment = new Comment();
                    newComment.setContent(commentDTO.getContent());
                    newComment.setAuthor(commentDTO.getAuthor());
                    newComment.setBlogPost(post);

                    Comment savedComment = commentRepository.save(newComment);
                    CommentDTO responseDTO = new CommentDTO(savedComment.getId(), savedComment.getContent(), savedComment.getAuthor(), savedComment.getBlogPost().getId());

                    return ResponseEntity.status(201).body(responseDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
