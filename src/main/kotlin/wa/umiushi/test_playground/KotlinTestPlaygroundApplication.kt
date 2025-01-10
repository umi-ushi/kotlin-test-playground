package wa.umiushi.test_playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<TestPlaygroundApplication>(*args)
}
