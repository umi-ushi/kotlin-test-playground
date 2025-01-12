package wa.umiushi.test_playground.sample

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.get
import org.apache.http.HttpStatus
import org.apache.http.protocol.HTTP
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestClient
import org.wiremock.spring.ConfigureWireMock
import org.wiremock.spring.EnableWireMock
import kotlin.test.assertEquals

@SpringBootTest
@EnableWireMock(
    ConfigureWireMock(
        port = 8088
    )
)
internal class WireMockSample(
    @Value("\${wiremock.server.baseUrl}")
    private val wireMockUrl: String
) {
    private val client: RestClient = RestClient.create("$wireMockUrl/external")

    @Test
    fun `get external api service`() {

        stubFor(
            get("/external/get").willReturn(
                aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader(HTTP.CONTENT_TYPE, "application/json")
                    .withBodyFile("get.json")
            )
        )

        val actual = client.get().uri("/get")
            .retrieve().body(SampleModel::class.java)

        assertEquals(SampleModel("success"), actual)
    }
}

data class SampleModel(
    val result: String = ""
)