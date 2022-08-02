package com.example.issuetracker.web

import com.example.issuetracker.config.AuthUser
import com.example.issuetracker.model.CommentRequest
import com.example.issuetracker.service.CommentService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable("issueId") issueId: Long,
        @RequestBody request: CommentRequest
    ) = commentService.create(issueId, authUser.userId, authUser.username, request)
}