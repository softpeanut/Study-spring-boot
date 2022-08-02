package com.example.issuetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class IssueTrackerApplication

fun main(args: Array<String>) {
    runApplication<IssueTrackerApplication>(*args)
}
