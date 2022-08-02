package com.example.issuetracker.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndUserId(commentId: Long, userId: Long): Comment?
}