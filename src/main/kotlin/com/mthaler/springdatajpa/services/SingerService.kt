package com.mthaler.springdatajpa.services

import com.mthaler.springdatajpa.entities.Singer

interface SingerService {

    fun findAll(): List<Singer>

    fun findByFirstName(firstName: String): List<Singer>

    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Singer>
}