package wa.umiushi.test_playground.sample

import org.junit.jupiter.api.*

internal class NestedTestSample {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setupAll() {
            println("setup all")
        }

        @JvmStatic
        @AfterAll
        fun tearDownAll() {
            println("teardown all")
        }
    }

    @BeforeEach
    fun setupEach() {
        println("setup each")
    }

    @AfterEach
    fun tearDownEach() {
        println("teardown each")
    }

    @Nested
    @DisplayName("Verifying the relationship between nested tests and various lifecycle methods")
    inner class OuterTestClass {

        @BeforeEach
        fun setupEach() {
            println("setup each nested")
        }

        @AfterEach
        fun tearDownEach() {
            println("teardown each nested")
        }

        @Test
        fun `nested tests`() {
            println("nested tests")
        }
    }

    @Test
    fun `non nested tests`() {
        println("non nested tests")
    }

}