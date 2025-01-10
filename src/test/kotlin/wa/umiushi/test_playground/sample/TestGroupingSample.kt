package wa.umiushi.test_playground.sample

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unit")
class UnitTest {

    @Test
    fun `unit test`() {
        println("unit test")
    }

}

@Tag("integration")
class IntegrationTest {

    @Test
    fun `integration test`() {
        println("integration test")
    }

}