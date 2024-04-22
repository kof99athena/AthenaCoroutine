import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun now() = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.MILLIS)

fun log(msg: String) = println("${now()}:${Thread.currentThread()}:${msg}")

fun launchInGlobalScope() {
    GlobalScope.launch {
        log("coroutine started.")
    }
}

fun main() {
    log("main() started") // step1
    launchInGlobalScope() // step3
    log("launchInGlobalScope() execute") // step2
    Thread.sleep(5000L) // wait step4 5 second
    log("main() terminated") // step4
}