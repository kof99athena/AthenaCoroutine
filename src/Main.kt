import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun now() = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.MILLIS)

fun log(msg: String) = println("${now()}:${Thread.currentThread()}:${msg}")

fun runBlockingExmaple() {
    runBlocking {
        launch {
            log("GlobalScope.launch started.")
        }
    }
}

fun main() {
    log("main() started") // step1
    runBlockingExmaple() // step3
    log("runBlockingExmaple() execute") // step2
    Thread.sleep(5000L) // wait step4 5 second
    log("main() terminated") // step4
}