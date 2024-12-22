package com.prosigliere.codingchallenge.service;

import com.prosigliere.codingchallenge.domain.dto.CommentDTO;
import com.prosigliere.codingchallenge.domain.model.Comment;
import com.prosigliere.codingchallenge.domain.model.BlogPost;
import com.prosigliere.codingchallenge.repository.BlogPostRepository;
import com.prosigliere.codingchallenge.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Adicionar um novo comentário a um post específico
    public CommentDTO addCommentToPost(Long postId, CommentDTO commentDTO) {
        BlogPost post = blogPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        Comment newComment = new Comment();
        newComment.setContent(commentDTO.getContent());
        newComment.setAuthor(commentDTO.getAuthor());
        newComment.setBlogPost(post);

        Comment savedComment = commentRepository.save(newComment);
        return new CommentDTO(savedComment.getId(), savedComment.getContent(), savedComment.getAuthor(), savedComment.getBlogPost().getId());
    }
}
