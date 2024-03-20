package com.colabear754.authentication_example.entity

import com.colabear754.authentication_example.common.MemberType
import com.colabear754.authentication_example.dto.MemberUpdateRequest
import com.colabear754.authentication_example.dto.SignUpRequest
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Member(
    @Column(nullable = false, scale = 20, unique = true)
    val account: String,
    @Column(nullable = false)
    var password: String,
    var name: String? = null,
    var age: Int? = null,
    @Enumerated(EnumType.STRING)
    val type: MemberType = MemberType.USER
) {
    val createdAt: LocalDateTime = LocalDateTime.now()
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
    companion object {
        fun from(request: SignUpRequest) = Member(
            account = request.account,
            password = request.password,
            name = request.name,
            age = request.age
        )
    }

    fun update(newMember: MemberUpdateRequest) {
        this.password = newMember.newPassword?.takeIf { it.isNotBlank() } ?: this.password
        this.name = newMember.name
        this.age = newMember.age
    }
}