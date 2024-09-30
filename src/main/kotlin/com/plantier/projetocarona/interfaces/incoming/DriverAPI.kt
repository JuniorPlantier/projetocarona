package com.plantier.projetocarona.interfaces.incoming

import com.plantier.projetocarona.domain.Driver
import com.plantier.projetocarona.domain.DriverRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class DriverAPI(
    val driverRepository: DriverRepository
) {
    @GetMapping("/drivers")
    fun listDrivers() : List<Driver> = driverRepository.findAll()

    @GetMapping("/drivers/{id}")
    fun findDriver(@PathVariable("id") id: Long) =
        driverRepository.findById(id).orElseThrow() {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @PostMapping("/drivers")
    fun createDriver(@RequestBody driver: Driver) : Driver =
        driverRepository.save(driver)

    @PutMapping("/drivers/{id}")
    fun fullUpdateDriver(@PathVariable id: Long, @RequestBody driver: Driver) : Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            name = driver.name,
            birthDate = driver.birthDate
        )
        return driverRepository.save(copyDriver)
    }

    @PatchMapping("/drivers/{id}")
    fun incrementalUpdateDriver(@PathVariable("id") id:Long, @RequestBody driver: PatchDriver) : Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            name = driver.name ?: foundDriver.name,
            birthDate = driver.birthDate ?: foundDriver.birthDate,
        )
        return driverRepository.save(copyDriver)
    }

    @DeleteMapping("/drivers/{id}")
    fun deleteDriver(@PathVariable("id") id: Long) =
        driverRepository.delete(findDriver(id))
}

data class PatchDriver(
    val name: String?,
    val birthDate: LocalDate?
)