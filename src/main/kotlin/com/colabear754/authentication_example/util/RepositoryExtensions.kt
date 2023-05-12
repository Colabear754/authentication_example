package com.colabear754.authentication_example.util

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import java.util.NoSuchElementException

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String = "해당 ID의 데이터가 존재하지 않습니다."): T = findByIdOrNull(id) ?: throw NoSuchElementException(message)
inline fun <reified T, ID, R> JpaRepository<T, ID>.flushOrThrow(exception: Throwable, block: JpaRepository<T, ID>.() -> R): R {
    try {
        val result = block()
        flush()
        return result
    } catch (e: DataIntegrityViolationException) {
        throw exception
    }
}