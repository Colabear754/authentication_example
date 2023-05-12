package com.colabear754.authentication_example.service

import com.colabear754.authentication_example.dto.SignInRequest
import com.colabear754.authentication_example.dto.SignInResponse
import com.colabear754.authentication_example.dto.SignUpRequest
import com.colabear754.authentication_example.dto.SignUpResponse
import com.colabear754.authentication_example.entity.Member
import com.colabear754.authentication_example.repository.MemberRepository
import com.colabear754.authentication_example.util.flushOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignService(private val memberRepository: MemberRepository) {
    @Transactional
    fun registMember(request: SignUpRequest) = SignUpResponse.from(
        memberRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) { save(Member.from(request)) }
    )

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val member = memberRepository.findByAccount(request.account)
            ?.takeIf { it.password == request.password } ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        return SignInResponse(member.name, member.type)
    }
}