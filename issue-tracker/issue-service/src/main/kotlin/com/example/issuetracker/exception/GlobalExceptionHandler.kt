package com.example.issuetracker.exception

import com.auth0.jwt.exceptions.TokenExpiredException
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ServerException::class)
    fun handleServerException(exception: ServerException): ErrorResponse {
        logger.error { exception.message }

        return ErrorResponse(
            code = exception.code,
            message = exception.message
        )
    }

    @ExceptionHandler(TokenExpiredException::class)
    fun handleTokenExpiredException(exception: TokenExpiredException): ErrorResponse {
        logger.error { exception.message }

        return ErrorResponse(
            code = 401,
            message = "Token Expired Error"
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ErrorResponse {
        logger.error { exception.message }

        return ErrorResponse(
            code = 500,
            message = "Internal Server Error"
        )
    }
}