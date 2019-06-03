import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

const val repeatTimes: Int = 100000

fun main() {
    val timeMillis = measureTimeMillis {
        runBlocking {
            repeat(times = repeatTimes) {
                launch {
                    ClawTest(verbose = false).run()
                }
            }
        }
    }
    println("Took $timeMillis millis to run $repeatTimes simulations!")
}