package com.mthaler.springdatajpa

import com.mthaler.springdatajpa.config.DataJpaConfig
import com.mthaler.springdatajpa.entities.Album
import com.mthaler.springdatajpa.entities.Singer
import com.mthaler.springdatajpa.services.AlbumService
import com.mthaler.springdatajpa.services.SingerService
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericApplicationContext
import java.util.function.Consumer

class SingerDataJPATest {

    @Test
    fun testFindAll() {
        val singers = singerService.findAll()
        assertTrue(singers.size > 0)
        listSingers(singers)
    }

    @Test
    fun testFindByFirstName() {
        val singers = singerService.findByFirstName("John")
        assertTrue(singers.size > 0)
        assertEquals(2, singers.size)
        listSingers(singers)
    }

    @Test
    fun testFindByFirstNameAndLastName() {
        val singers = singerService.findByFirstNameAndLastName("John", "Mayer")
        assertTrue(singers.size > 0)
        assertEquals(1, singers.size)
        listSingers(singers)
    }

    @Test
    fun testFindBySinger() {
        val singers = singerService.findByFirstNameAndLastName("John", "Mayer")
        assertTrue(singers.size > 0)
        assertEquals(1, singers.size)
        val singer = singers[0]
        val albums = albumService.findBySinger(singer)
        assertTrue(albums.size > 0)
        assertEquals(2, albums.size)
        albums.forEach(Consumer { a: Album -> logger.info(a.toString()) })
    }

    @Test
    fun testFindByTitle() {
        val albums = albumService.findByTitle("The")
        assertTrue(albums.size > 0)
        assertEquals(2, albums.size)
        albums.forEach(Consumer { a: Album ->
            logger.info(
                a.toString() + ", Singer: " + a.singer!!.firstName + " "
                        + a.singer!!.lastName
            )
        })
    }

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(SingerDataJPATest::class.java)

        private lateinit var ctx: GenericApplicationContext
        private lateinit var singerService: SingerService
        private lateinit var albumService: AlbumService

        @JvmStatic
        @BeforeAll
        fun setUp() {
            ctx = AnnotationConfigApplicationContext(DataJpaConfig::class.java)
            singerService = ctx.getBean<SingerService>(SingerService::class.java)
            albumService = ctx.getBean(AlbumService::class.java)
            assertNotNull(singerService)
            assertNotNull(albumService)
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            ctx.close()
        }

        private fun listSingers(singers: List<Singer>) {
            logger.info(" ---- Listing singers:")
            for (singer in singers) {
                logger.info(singer.toString())
            }
        }
    }
}