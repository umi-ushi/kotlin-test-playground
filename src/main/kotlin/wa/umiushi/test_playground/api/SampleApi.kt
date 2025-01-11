package wa.umiushi.test_playground.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sample")
class SampleApi {

    @GetMapping("/get")
    fun getSample(): String {
        return "call get sample"
    }

    @PostMapping("/post")
    fun postSample(@RequestParam name: String): String {
        return "call post sample by $name"
    }
}