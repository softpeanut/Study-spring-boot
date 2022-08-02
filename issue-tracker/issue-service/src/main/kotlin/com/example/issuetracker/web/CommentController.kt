package com.example.issuetracker.web

import com.example.issuetracker.config.AuthUser
import com.example.issuetracker.model.CommentRequest
import com.example.issuetracker.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping("/{issueId}/comments")
    fun create(
        authUser: AuthUser,
        @PathVariable("issueId") issueId: Long,
        @RequestBody request: CommentRequest
    ) = commentService.create(issueId, authUser.userId, authUser.username, request)

    @PutMapping("/{commentId}")
    fun edit(
        authUser: AuthUser,
        @PathVariable("commentId") commentId: Long,
        @RequestBody request: CommentRequest
    ) = commentService.edit(commentId, authUser.userId, authUser.username, request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    fun delete(
        authUser: AuthUser,
        @PathVariable("commentId") commentId: Long
    ) {
        commentService.delete(commentId, authUser.userId)
    }
}