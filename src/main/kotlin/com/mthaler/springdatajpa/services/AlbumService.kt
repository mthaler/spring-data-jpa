package com.mthaler.springdatajpa.services

import com.mthaler.springdatajpa.entities.Album
import com.mthaler.springdatajpa.entities.Singer

interface AlbumService {

    fun findBySinger(singer: Singer): List<Album>

    fun findByTitle(title: String): List<Album>
}