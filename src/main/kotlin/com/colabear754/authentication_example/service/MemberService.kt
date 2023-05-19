package com.colabear754.authentication_example.service

import com.colabear754.authentication_example.dto.MemberDeleteResponse
import com.colabear754.authentication_example.dto.MemberInfoResponse
import com.colabear754.authentication_example.dto.MemberUpdateRequest
import com.colabear754.authentication_example.dto.MemberUpdateResponse
import com.colabear754.authentication_example.repository.MemberRepository
import com.colabear754.authentication_example.util.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) {
    @Transactional(readOnly = true)
    fun getMemberInfo(id: UUID) = MemberInfoResponse.from(memberRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다."))

    @Transactional
    fun deleteMember(id: UUID): MemberDeleteResponse {
        if (!memberRepository.existsById(id)) return MemberDeleteResponse(false)
        memberRepository.deleteById(id)
        return MemberDeleteResponse(true)
    }

    @Transactional
    fun updateMember(id: UUID, request: MemberUpdateRequest): MemberUpdateResponse {
        val member = memberRepository.findByIdOrNull(id)?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        member.update(request, encoder)
        return MemberUpdateResponse.of(true, member)
    }
}