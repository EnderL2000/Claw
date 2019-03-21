class ClawApplication {

    private var screens = listOf<Screen>()
    private var state = States.SCREEN
    var currentScreen: Screen = Screen(clawApp = this)

    fun addScreen(screen: Screen) {
        screens = screens + screen
    }

    fun usePortal(portal: Portal) {
        if(state == States.TRANSITION) return

        state = States.TRANSITION
        currentScreen = portal.destination
        state = States.SCREEN
    }

    fun handleClick(x: Int, y: Int) {
        if(state == States.TRANSITION) return

        currentScreen.interactables.find { it.boundsContain(x = x, y = y) }?.onClick() ?: return
    }

    override fun toString(): String {
        return "Claw Application: Current Screen = ${currentScreen.name}"
    }

    enum class States {
        TRANSITION,
        SCREEN
    }
}