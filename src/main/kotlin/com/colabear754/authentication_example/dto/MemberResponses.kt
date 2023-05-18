package com.colabear754.authentication_example.dto

import com.colabear754.authentication_example.common.MemberType
import com.colabear754.authentication_example.entity.Member
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

data class SignUpResponse(
    @Schema(description = "회원 고유키", example = "123e4567-e89b-12d3-a456-426614174000")
    val id: UUID,
    @Schema(description = "회원 아이디", example = "colabear754")
    val account: String,
    @Schema(description = "회원 이름", example = "콜라곰")
    val name: String?,
    @Schema(description = "회원 나이", example = "27")
    val age: Int?
) {
    companion object {
        fun from(member: Member) = SignUpResponse(
            id = member.id!!,
            account = member.account,
            name = member.name,
            age = member.age
        )
    }
}

data class SignInResponse(
    @Schema(description = "회원 이름", example = "콜라곰")
    val name: String?,
    @Schema(description = "회원 유형", example = "USER")
    val type: MemberType,
    val token: String
)

data class MemberUpdateResponse(
    @Schema(description = "회원 정보 수정 성공 여부", example = "true")
    val result: Boolean,
    @Schema(description = "회원 이름", example = "콜라곰")
    val name: String?,
    @Schema(description = "회원 나이", example = "27")
    val age: Int?
) {
    companion object {
        fun of(result: Boolean, member: Member) = MemberUpdateResponse(
            result = result,
            name = member.name,
            age = member.age
        )
    }
}

data class MemberInfoResponse(
    @Schema(description = "회원 고유키", example = "123e4567-e89b-12d3-a456-426614174000")
    val id: UUID,
    @Schema(description = "회원 아이디", example = "colabear754")
    val account: String,
    @Schema(description = "회원 이름", example = "콜라곰")
    val name: String?,
    @Schema(description = "회원 나이", example = "27")
    val age: Int?,
    @Schema(description = "회원 타입", example = "USER")
    val type: MemberType,
    @Schema(description = "회원 생성일", example = "2023-05-11T15:00:00")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(member: Member) = MemberInfoResponse(
            id = member.id!!,
            account = member.account,
            name = member.name,
            age = member.age,
            type = member.type,
            createdAt = member.createdAt
        )
    }
}

data class MemberDeleteResponse(
    @Schema(description = "회원 삭제 성공 여부", example = "true")
    val result: Boolean
)