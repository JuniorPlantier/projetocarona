package com.plantier.projetocarona.interfaces.outcoming

import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GMapsService(
    @Value("\${app.projetocarona.domain.googlemaps.apikey}")
    val appKey: String
) {

    val GMAPS_TEMPLATE: String = "https://maps.googleapis.com/maps/api/directions/json?origin={origin}&destination={destination}&key={key}"


    fun getDistanceBetweenAddress(addressOne: String, addressTwo: String): Int {
//        val template = RestTemplate() // Classe que fornece métodos para consumirmos serviços REST.
//        val jsonResult = template.getForObject(GMAPS_TEMPLATE, String::class.java, addressOne, addressTwo, appKey)
//        val rawResults: List<Int> = JsonPath.parse(jsonResult).read("\$..legs[*].duration.value")


        val jsonResult = "    {\n" +
                "       \"geocoded_waypoints\" : [\n" +
                "          {\n" +
                "             \"geocoder_status\" : \"OK\",\n" +
                "             \"place_id\" : \"ChIJRVY_etDX3IARGYLVpoq7f68\",\n" +
                "             \"types\" : [\n" +
                "                \"bus_station\",\n" +
                "                \"transit_station\",\n" +
                "                \"point_of_interest\",\n" +
                "                \"establishment\"\n" +
                "             ]\n" +
                "          },\n" +
                "          {\n" +
                "             \"geocoder_status\" : \"OK\",\n" +
                "             \"partial_match\" : true,\n" +
                "             \"place_id\" : \"ChIJp2Mn4E2-woARQS2FILlxUzk\",\n" +
                "             \"types\" : [ \"route\" ]\n" +
                "          }\n" +
                "       ],\n" +
                "       \"routes\" : [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t \"bounds\" : {\n" +
                "\t\t\t\t\t\"northeast\" : {\n" +
                "\t\t\t\t\t   \"lat\" : 34.1330949,\n" +
                "\t\t\t\t\t   \"lng\" : -117.9143879\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"southwest\" : {\n" +
                "\t\t\t\t\t   \"lat\" : 33.8068768,\n" +
                "\t\t\t\t\t   \"lng\" : -118.3527671\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t },\n" +
                "\t\t\t\t \"copyrights\" : \"Map data ©2016 Google\",\n" +
                "\t\t\t\t \"legs\" : [\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t   \"distance\" : {\n" +
                "\t\t\t\t\t\t  \"text\" : \"35.9 mi\",\n" +
                "\t\t\t\t\t\t  \"value\" : 57824\n" +
                "\t\t\t\t\t   },\n" +
                "\t\t\t\t\t   \"duration\" : {\n" +
                "\t\t\t\t\t\t  \"text\" : \"9 mins\",\n" +
                "\t\t\t\t\t\t  \"value\" : 540\n" +
                "\t\t\t\t\t   },\n" +
                "\t\t\t\t\t   \"end_address\" : \"Universal Studios Blvd, Los Angeles, CA 90068, USA\",\n" +
                "\t\t\t\t\t   \"end_location\" : {\n" +
                "\t\t\t\t\t\t  \"lat\" : 34.1330949,\n" +
                "\t\t\t\t\t\t  \"lng\" : -118.3524442\n" +
                "\t\t\t\t\t   },\n" +
                "\t\t\t\t\t   \"start_address\" : \"Disneyland (Harbor Blvd.), S Harbor Blvd, Anaheim, CA 92802, USA\",\n" +
                "\t\t\t\t\t   \"start_location\" : {\n" +
                "\t\t\t\t\t\t  \"lat\" : 33.8098177,\n" +
                "\t\t\t\t\t\t  \"lng\" : -117.9154353\n" +
                "\t\t\t\t\t   }\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"status\" : \"OK\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}"

        val rawResults: List<Int> = JsonPath.parse(jsonResult).read("\$..legs[*].duration.value")

        return rawResults.minOfOrNull { it as Int } ?: Int.MAX_VALUE
    }
}