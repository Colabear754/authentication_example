package com.colabear754.authentication_example

import com.colabear754.authentication_example.common.MemberType
import com.colabear754.authentication_example.entity.Member
import com.colabear754.authentication_example.repository.MemberRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AdminInitializer(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        memberRepository.save(Member("admin", encoder.encode("admin"), "관리자", type = MemberType.ADMIN))
    }
}