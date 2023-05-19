package com.colabear754.authentication_example.service

import com.colabear754.authentication_example.dto.MemberUpdateRequest
import com.colabear754.authentication_example.entity.Member
import com.colabear754.authentication_example.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@SpringBootTest
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) {
    @BeforeEach
    @AfterEach
    fun clear() {
        memberRepository.deleteAll()
    }

    @Test
    fun 회원조회() {
        // given
        val savedMember = memberRepository.save(Member("colabear754", "1234", "콜라곰", 27))
        // when
        val member = memberService.getMemberInfo(savedMember.id!!)
        // then
        assertThat(member.id).isEqualTo(savedMember.id)
        assertThat(member.account).isEqualTo("colabear754")
        assertThat(member.name).isEqualTo("콜라곰")
        assertThat(member.age).isEqualTo(27)
    }

    @Test
    fun `존재하지 않는 회원을 조회하면 예외가 발생한다`() {
        // given
        // when
        // then
        assertThrows(NoSuchElementException::class.java) {
            memberService.getMemberInfo(UUID.randomUUID())
        }.also { assertThat(it.message).isEqualTo("존재하지 않는 회원입니다.") }
    }

    @Test
    fun 회원탈퇴() {
        // given
        val savedMember = memberRepository.save(Member("colabear754", "1234"))
        // when
        val result = memberService.deleteMember(savedMember.id!!)
        // then
        val members = memberRepository.findAll()
        assertThat(members).isEmpty()
        assertThat(result.result).isEqualTo(true)
    }

    @Test
    fun 회원정보수정() {
        // given
        val savedMember = memberRepository.save(Member("colabear754", encoder.encode("1234")))
        // when
        val request = MemberUpdateRequest("1234", "5678", "콜라곰", 27)
        val result = memberService.updateMember(savedMember.id!!, request)
        // then
        assertThat(result.result).isEqualTo(true)
        assertThat(result.name).isEqualTo("콜라곰")
        assertThat(result.age).isEqualTo(27)
        val member = memberRepository.findAll()[0]
        assertThat(encoder.matches("5678", member.password)).isEqualTo(true)
    }
}