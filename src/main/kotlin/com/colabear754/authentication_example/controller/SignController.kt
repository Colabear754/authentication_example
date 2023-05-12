package com.colabear754.authentication_example.controller

import com.colabear754.authentication_example.dto.ApiResponse
import com.colabear754.authentication_example.dto.SignInRequest
import com.colabear754.authentication_example.dto.SignUpRequest
import com.colabear754.authentication_example.service.SignService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원 가입 및 로그인 API")
@RestController
@RequestMapping
class SignController(private val signService: SignService) {
    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest) = ApiResponse.success(signService.registMember(request))

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest) = ApiResponse.success(signService.signIn(request))
}