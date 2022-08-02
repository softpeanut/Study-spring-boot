package com.example.issuetracker.service

import com.example.issuetracker.domain.Issue
import com.example.issuetracker.domain.IssueRepository
import com.example.issuetracker.domain.enums.IssueStatus
import com.example.issuetracker.exception.NotFoundException
import com.example.issuetracker.model.IssueRequest
import com.example.issuetracker.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
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

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)?.map { IssueResponse(it) }

    @Transactional(readOnly = true)
    fun get(issueId: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
        return IssueResponse(issue)
    }

    @Transactional
    fun edit(userId: Long, issueId: Long, request: IssueRequest): IssueResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            type = request.type
            priority = request.priority
            status = request.status
            IssueResponse(issueRepository.save(this))
        }
    }

    @Transactional
    fun delete(issueId: Long) {
        issueRepository.deleteById(issueId)
    }
}