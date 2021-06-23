package com.mthaler.springdatajpa

import com.mthaler.springdatajpa.config.DataJpaConfig
import com.mthaler.springdatajpa.entities.Singer
import com.mthaler.springdatajpa.services.SingerService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
    val ctx = AnnotationConfigApplicationContext(DataJpaConfig::class.java)
    val singerService = ctx.getBean(
        "springJpaSingerService", SingerService::class.java
    )
    listSingers("Find all:", singerService.findAll())
    listSingers("Find by first name:", singerService.findByFirstName("John"))
    listSingers(
        "Find by first and last name:",
        singerService.findByFirstNameAndLastName("John", "Mayer")
    )
}

private fun listSingers(message: String, singers: List<Singer>) {
    println("")
    println(message)
    for (singer in singers) {
        println(singer)
        println()
    }
}