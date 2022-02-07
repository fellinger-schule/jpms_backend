package be.rgen

import be.rgen.dto.artist.ArtistAddDTO
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder


@QuarkusTest

class ArtistResourceTest {
    @Test
    fun testAddArtist() {
        RestAssured
        .given()
                .contentType(ContentType.JSON)
                .body(ArtistAddDTO("Metallica"))
        .`when`()
                .post("/artist/add")
        .then()
                .statusCode(200)
    }
}