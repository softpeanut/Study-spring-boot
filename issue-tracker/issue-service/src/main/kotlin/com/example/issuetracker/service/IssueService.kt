package com.example.issuetracker.service

import com.example.issuetracker.domain.Issue
import com.example.issuetracker.domain.IssueRepository
import com.example.issuetracker.model.IssueRequest
import com.example.issuetracker.model.IssueResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status
        )

        return IssueResponse(issueRepository.save(issue))
    }
}