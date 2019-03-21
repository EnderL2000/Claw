abstract class Transition(val clawApplication: ClawApplication) {
    var prevScreen: Screen = clawApplication.currentScreen
    var nextScreen: Screen = clawApplication.currentScreen

    fun transition(deltaTime: Double) {
        if(isFinished()) {
            end()
        }
    }

    fun start() {
        clawApplication.state = ClawApplication.States.TRANSITION
    }

    fun end() {
        clawApplication.state = ClawApplication.States.SCREEN
    }

    fun isFinished() : Boolean = true
}