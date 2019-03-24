abstract class Transition(val clawApplication: ClawApplication) {
    var prevScreen: Screen = clawApplication.currentScreen
    var nextScreen: Screen = clawApplication.currentScreen

    fun bind() {
        clawApplication.transition = this
    }

    open fun transition(deltaTime: Double) {
        if(isFinished()) {
            end()
        }
    }

    open fun start() {
        clawApplication.state = ClawApplication.States.TRANSITION
    }

    open fun end() {
        clawApplication.state = ClawApplication.States.SCREEN
    }

    abstract fun isFinished() : Boolean
}