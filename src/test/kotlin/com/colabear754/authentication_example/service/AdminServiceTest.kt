package com.colabear754.authentication_example.service

import com.colabear754.authentication_example.common.MemberType
import com.colabear754.authentication_example.entity.Member
import com.colabear754.authentication_example.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AdminServiceTest @Autowired constructor(
    private val adminService: AdminService,
    private val memberRepository: MemberRepository
) {
    @BeforeEach
    @AfterEach
    fun clear() {
        memberRepository.deleteAll()
    }

    @Test
    fun `관리자는 모든 회원정보를 조회할 수 있다`() {
        // given
        memberRepository.save(Member("colabear754", "1234", "콜라곰"))
        memberRepository.save(Member("ciderbear754", "1234", "사이다곰"))
        memberRepository.save(Member("fantabear754", "1234", "환타곰"))
        // when
        val members = adminService.getMembers()
        // then
        assertThat(members).hasSize(3)
        assertThat(members[0].account).isEqualTo("colabear754")
        assertThat(members[0].name).isEqualTo("콜라곰")
        assertThat(members[0].type).isEqualTo(MemberType.USER)
        assertThat(members[1].account).isEqualTo("ciderbear754")
        assertThat(members[1].name).isEqualTo("사이다곰")
        assertThat(members[1].type).isEqualTo(MemberType.USER)
        assertThat(members[2].account).isEqualTo("fantabear754")
        assertThat(members[2].name).isEqualTo("환타곰")
        assertThat(members[2].type).isEqualTo(MemberType.USER)
    }

    @Test
    fun `관리자는 모든 관리자정보를 조회할 수 있다`() {
        // given
        memberRepository.save(Member("colabear754", "1234", "콜라곰", null, MemberType.ADMIN))
        memberRepository.save(Member("ciderbear754", "1234", "사이다곰", null, MemberType.ADMIN))
        memberRepository.save(Member("fantabear754", "1234", "환타곰", null, MemberType.ADMIN))
        // when
        val admins = adminService.getAdmins()
        // then
        assertThat(admins).hasSize(3)
        assertThat(admins[0].account).isEqualTo("colabear754")
        assertThat(admins[0].name).isEqualTo("콜라곰")
        assertThat(admins[0].type).isEqualTo(MemberType.ADMIN)
        assertThat(admins[1].account).isEqualTo("ciderbear754")
        assertThat(admins[1].name).isEqualTo("사이다곰")
        assertThat(admins[1].type).isEqualTo(MemberType.ADMIN)
        assertThat(admins[2].account).isEqualTo("fantabear754")
        assertThat(admins[2].name).isEqualTo("환타곰")
        assertThat(admins[2].type).isEqualTo(MemberType.ADMIN)
    }
}