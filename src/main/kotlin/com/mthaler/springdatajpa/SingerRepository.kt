package com.mthaler.springdatajpa

import org.springframework.data.repository.CrudRepository

interface SingerRepository: CrudRepository<Singer, Long> {

    fun findByFirstName(firstName: String): List<Singer>

    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Singer>
}