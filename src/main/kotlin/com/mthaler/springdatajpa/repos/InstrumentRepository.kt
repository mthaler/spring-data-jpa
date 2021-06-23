package com.mthaler.springdatajpa.repos

import com.mthaler.springdatajpa.entities.Instrument
import org.springframework.data.jpa.repository.JpaRepository

interface InstrumentRepository: JpaRepository<Instrument, Long>