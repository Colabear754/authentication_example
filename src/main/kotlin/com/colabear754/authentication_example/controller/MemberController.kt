package com.colabear754.authentication_example.controller

import com.colabear754.authentication_example.dto.ApiResponse
import com.colabear754.authentication_example.dto.MemberUpdateRequest
import com.colabear754.authentication_example.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.*

@Tag(name = "로그인 후 사용할 수 있는 API")
@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {
    @Operation(summary = "회원 정보 조회")
    @GetMapping
    fun getMemberInfo(id: String) = ApiResponse.success(memberService.getMemberInfo(UUID.fromString(id)))

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    fun deleteMember(id: String) = ApiResponse.success(memberService.deleteMember(UUID.fromString(id)))

    @Operation(summary = "회원 정보 수정")
    @PutMapping
    fun updateMember(id: String, @RequestBody request: MemberUpdateRequest) = ApiResponse.success(memberService.updateMember(UUID.fromString(id), request))
}