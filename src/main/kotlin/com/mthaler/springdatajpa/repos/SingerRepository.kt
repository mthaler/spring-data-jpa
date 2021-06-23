package com.mthaler.springdatajpa.repos

import com.mthaler.springdatajpa.entities.Singer
import org.springframework.data.repository.CrudRepository

interface SingerRepository: CrudRepository<Singer, Long> {

    fun findByFirstName(firstName: String): List<Singer>

    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Singer>
}