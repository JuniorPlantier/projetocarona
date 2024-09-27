package com.plantier.projetocarona.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
data class Driver (

    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String,
    val birthDate: LocalDate
)

@Entity
data class Passenger (
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String
)

@Entity
data class TravelRequest(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    val passenger: Passenger,
    val origin: String,
    val destination: String
)