import io.gatling.javaapi.core.CoreDsl.constantUsersPerSec
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder

class SimulationSample : Simulation() {

    val httpProtocol: HttpProtocolBuilder = http
        .baseUrl("http://localhost:8080")
        .acceptHeader("application/json")

    val scenario: ScenarioBuilder = scenario("example scenario")
        .exec(
            http("Request Test")
                .get("/sample/get")
        )

    // Add the setUp block:
    init {
        setUp(
            scenario.injectOpen(constantUsersPerSec(2.0).during(60))
        ).protocols(httpProtocol)
    }
}

