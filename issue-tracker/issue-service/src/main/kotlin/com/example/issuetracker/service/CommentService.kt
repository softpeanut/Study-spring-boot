package com.example.issuetracker.service

import com.example.issuetracker.domain.Comment
import com.example.issuetracker.domain.CommentRepository
import com.example.issuetracker.domain.IssueRepository
import com.example.issuetracker.model.CommentRequest
import com.example.issuetracker.model.CommentResponse
import com.example.issuetracker.model.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest): CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IllegalArgumentException("이슈가 존재하지 않습니다.")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(commentId: Long, userId: Long, username: String, request: CommentRequest): CommentResponse? =
        commentRepository.findByIdAndUserId(commentId, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
}