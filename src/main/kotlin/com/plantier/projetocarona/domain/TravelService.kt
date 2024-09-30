package com.plantier.projetocarona.domain

import com.plantier.projetocarona.interfaces.outcoming.GMapsService
import org.springframework.stereotype.Component

@Component
class TravelService(
    val travelRequestRepository: TravelRequestRepository,
    val gMapsService: GMapsService
) {

    val MAX_TRAVEL_TIME: Int = 600
    fun saveTravelRequest(travelRequest: TravelRequest) =
        travelRequestRepository.save(travelRequest)

    fun listNearbyTravelRequests(currentAddress: String): List<TravelRequest> {
        val requests = travelRequestRepository.findByStatus(TravelRequestStatus.CREATED)
        return requests.filter { tr ->
            gMapsService.getDistanceBetweenAddress(currentAddress, tr.origin) < MAX_TRAVEL_TIME
        }
    }
}