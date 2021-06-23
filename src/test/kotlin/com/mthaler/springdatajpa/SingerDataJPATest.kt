package com.mthaler.springdatajpa

import com.mthaler.springdatajpa.config.DataJpaConfig
import com.mthaler.springdatajpa.entities.Singer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericApplicationContext

class SingerDataJPATest {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(SingerDataJPATest::class.java)

        private lateinit var ctx: GenericApplicationContext
        private lateinit var singerService: SingerService

        @JvmStatic
        @BeforeAll
        fun setUp() {
            ctx = AnnotationConfigApplicationContext(DataJpaConfig::class.java)
            singerService = ctx.getBean<SingerService>(SingerService::class.java)
            assertNotNull(singerService)
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