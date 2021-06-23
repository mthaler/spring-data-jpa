package com.mthaler.springdatajpa

import com.mthaler.springdatajpa.entities.Singer
import org.springframework.context.support.GenericXmlApplicationContext

fun main(args: Array<String>) {
    val ctx = GenericXmlApplicationContext()
    ctx.load("classpath:spring/app-context-annotation.xml")
    ctx.refresh()
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