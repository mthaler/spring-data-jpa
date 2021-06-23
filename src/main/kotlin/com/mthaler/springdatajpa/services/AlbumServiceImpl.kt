package com.mthaler.springdatajpa.services

import com.mthaler.springdatajpa.entities.Album
import com.mthaler.springdatajpa.entities.Singer
import com.mthaler.springdatajpa.repos.AlbumRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("springJpaAlbumService")
@Transactional
class AlbumServiceImpl: AlbumService {

    @Autowired
    private lateinit var albumRepository: AlbumRepository

    @Transactional(readOnly = true)
    override fun findBySinger(singer: Singer): List<Album> {
        return albumRepository.findBySinger(singer)
    }

    override fun findByTitle(title: String): List<Album> {
        return albumRepository.findByTitle(title)
    }
}