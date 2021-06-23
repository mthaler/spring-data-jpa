package com.mthaler.springdatajpa.services

import com.mthaler.springdatajpa.entities.Singer
import com.mthaler.springdatajpa.repos.SingerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("springJpaSingerService")
@Transactional
class SingerServiceImpl: SingerService {

    @Autowired
    private lateinit var singerRepository: SingerRepository

    @Transactional(readOnly = true)
    override fun findAll(): List<Singer> {
        return singerRepository.findAll().toList()
    }

    @Transactional(readOnly = true)
    override fun findByFirstName(firstName: String): List<Singer> {
        return singerRepository.findByFirstName(firstName)
    }

    @Transactional(readOnly = true)
    override fun findByFirstNameAndLastName(firstName: String, lastName: String): List<Singer> {
        return singerRepository.findByFirstNameAndLastName(firstName, lastName)
    }
}