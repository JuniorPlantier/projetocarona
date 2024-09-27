package com.plantier.projetocarona.interfaces

import com.plantier.projetocarona.domain.Passenger
import com.plantier.projetocarona.domain.PassengerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Service
@RestController
@RequestMapping(path = ["/passengers"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PassengerAPI(
    val passengerRepository: PassengerRepository
) {

    @GetMapping
    fun listPassengers() = passengerRepository.findAll()

    @GetMapping("/{id}")
    fun findPassenger(@PathVariable("id") id: Long) =
        passengerRepository.findById(id).orElseThrow() {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @PostMapping
    fun createPassenger(@RequestBody passenger: Passenger) =
        passengerRepository.save(passenger)

    @PutMapping("/{id}")
    fun fullUpdatePassenger(@RequestBody passenger: Passenger,
                            @PathVariable("id") id: Long) : Passenger {
        val newPassenger = findPassenger(id).copy(
            name = passenger.name
        )
        return passengerRepository.save(newPassenger)
    }

    @PatchMapping("/{id}")
    fun incrementalUpdatePassenger(
            @RequestBody passenger: PatchPassenger,
            @PathVariable("id") id: Long) : Passenger {
        val foundPassenger = findPassenger(id)
        val newPassenger = findPassenger(id).copy(
            name = passenger.name ?: foundPassenger.name
        )
        return passengerRepository.save(newPassenger)
    }

    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long) =
        passengerRepository.delete(findPassenger(id))
}

data class PatchPassenger(
    val name: String?
)