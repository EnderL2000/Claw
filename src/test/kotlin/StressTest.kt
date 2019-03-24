import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val repeatTimes: Int = 100000

fun main() {
    val startTime = System.currentTimeMillis()
    runBlocking {
        repeat(repeatTimes) {
            launch {
                ClawTest(verbose = false).run()
            }
        }
    }
    val endTime = System.currentTimeMillis()
    println("Took ${endTime - startTime} millis to run $repeatTimes simulations!")
}