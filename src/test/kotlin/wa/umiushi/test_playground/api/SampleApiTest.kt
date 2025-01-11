package wa.umiushi.test_playground.api

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RestAssuredSampleTest {

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = 8080
        RestAssured.basePath = "/sample"
    }

    @Test
    fun `sample of get method`() {
        val response = given().`when`().get("/get").then().statusCode(200).extract().response()
        assertEquals("call get sample", response.body.asString())
    }

    @Test
    fun `sample of post method`() {
        val response =
            given().params("name", "umiushi").`when`().post("/post").then().statusCode(200).extract().response()
        assertEquals("call post sample by umiushi", response.body.asString())
    }
}