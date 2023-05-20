package com.colabear754.authentication_example.controller

import com.colabear754.authentication_example.dto.ApiResponse
import com.colabear754.authentication_example.security.AdminAuthorize
import com.colabear754.authentication_example.service.AdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자용 API")
@AdminAuthorize
@RestController
@RequestMapping("/admin")
class AdminController(private val adminService: AdminService) {
    @Operation(summary = "회원 목록 조회")
    @GetMapping("/members")
    fun getAllMembers() = ApiResponse.success(adminService.getMembers())

    @Operation(summary = "관리자 목록 조회")
    @GetMapping("/admins")
    fun getAllAdmins() = ApiResponse.success(adminService.getAdmins())
}