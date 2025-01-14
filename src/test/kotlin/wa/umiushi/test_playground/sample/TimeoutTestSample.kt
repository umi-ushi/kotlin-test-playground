package wa.umiushi.test_playground.sample

import com.github.tomakehurst.wiremock.client.WireMock.*
import org.apache.http.HttpStatus
import org.apache.http.protocol.HTTP
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestClient
import org.wiremock.spring.ConfigureWireMock
import org.wiremock.spring.EnableWireMock
import java.net.SocketTimeoutException

@SpringBootTest
@EnableWireMock(
    ConfigureWireMock(
        port = 8088
    )
)
internal class TimeoutTestSample(
    @Value("\${wiremock.server.baseUrl}")
    private val wireMockUrl: String
) {

    private val client: RestClient = RestClient
        .builder()
        .requestFactory(HttpComponentsClientHttpRequestFactory().also {
            it.setConnectionRequestTimeout(500)
            it.setConnectTimeout(500)
            it.setReadTimeout(500)
        })
        .baseUrl("$wireMockUrl/external")
        .build()

    @Test
    fun `time out then get timeout exception`() {

        stubFor(
            get("/external/get").willReturn(
                aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader(HTTP.CONTENT_TYPE, "application/json")
                    .withBodyFile("get.json")
                    .withFixedDelay(1000)
            )
        )

        val exception = assertThrows<ResourceAccessException> {
            client.get().uri("/get")
                .retrieve().body(SampleModel::class.java)
        }

        assertInstanceOf<SocketTimeoutException>(exception.cause)
    }
}