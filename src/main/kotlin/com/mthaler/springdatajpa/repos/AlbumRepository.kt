package com.mthaler.springdatajpa.repos

import com.mthaler.springdatajpa.entities.Album
import com.mthaler.springdatajpa.entities.Singer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface AlbumRepository: JpaRepository<Album, Long> {

    fun findBySinger(singer: Singer): List<Album>

    @Query("select a from Album a where a.title like %:title%")
    fun findByTitle(@Param("title") title: String): List<Album>
}