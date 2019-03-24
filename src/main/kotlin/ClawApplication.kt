class ClawApplication {

    private var screens = listOf<Screen>()
    var state = States.SCREEN
    var currentScreen: Screen = Screen(clawApp = this)
    var transition: Transition? = null
    val gameData = GameData()

    fun addScreen(screen: Screen) {
        screens = screens + screen
    }

    fun getScreenByName(name: String) : Screen? {
        return screens.find { it.name == name }
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
        //Don't handle input if transitioning
        if(state == States.TRANSITION) return

        //Get the object in the top most z-layer and call its onClick
        currentScreen.interactables.find { it.boundsContain(x = x, y = y) }?.onClick() ?: return
    }

    fun update(deltaTime: Double) {
        //Transition only if we're already transitioning
        if(state != States.TRANSITION) return

        transition?.execute(deltaTime = deltaTime)
    }

    override fun toString(): String {
        return "Claw Application: Current Screen = ${currentScreen.name}"
    }

    enum class States {
        TRANSITION,
        SCREEN
    }
}