package com.colabear754.authentication_example.handler

import com.colabear754.authentication_example.dto.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.colabear754.authentication_example.controller"])
class ExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class, NoSuchElementException::class)
    fun handleCommonException(e: Exception) = ResponseEntity.badRequest().body(ApiResponse.error(e.message))

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException() = ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error("접근이 거부되었습니다."))

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException() = ResponseEntity.internalServerError().body(ApiResponse.error("서버에 문제가 발생했습니다."))
}