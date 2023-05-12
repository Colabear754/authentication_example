package com.colabear754.authentication_example.service

import com.colabear754.authentication_example.common.MemberType
import com.colabear754.authentication_example.dto.MemberInfoResponse
import com.colabear754.authentication_example.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = true)
    fun getMembers(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.USER).map(MemberInfoResponse::from)

    @Transactional(readOnly = true)
    fun getAdmins(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.ADMIN).map(MemberInfoResponse::from)
}