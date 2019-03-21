class ClawApplication(val transition: Transition? = null) {

    private var screens = listOf<Screen>()
    var state = States.SCREEN
    var currentScreen: Screen = Screen(clawApp = this)

    fun addScreen(screen: Screen) {
        screens = screens + screen
    }

    fun usePortal(portal: Portal) {
        //We can't move if we're already moving
        if(state == States.TRANSITION) return

        //Give the transition-er the two screens we're moving between, and start it
        transition?.prevScreen = currentScreen
        transition?.nextScreen = portal.destination
        currentScreen = portal.destination
        transition?.start()
    }

    fun handleClick(x: Int, y: Int) {
        if(state == States.TRANSITION) return

        currentScreen.interactables.find { it.boundsContain(x = x, y = y) }?.onClick() ?: return
    }

    fun transition(deltaTime: Double) {
        if(state != States.TRANSITION) return

        transition?.transition(deltaTime)
    }

    override fun toString(): String {
        return "Claw Application: Current Screen = ${currentScreen.name}"
    }

    enum class States {
        TRANSITION,
        SCREEN
    }
}