fun main() {
    ClawTest(verbose = true).run()
}

class ClawTest(private val verbose: Boolean = false) {

    private val clawApplication = ClawApplication()

    private val transitionEngine = object : Transition(clawApplication = clawApplication) {
        override fun transition(deltaTime: Double) {
            if (verbose) println("Transitioning...")
            super.transition(deltaTime = deltaTime)
        }

        override fun start() {
            if (verbose) println("Transition Started")
            super.start()
        }

        override fun end() {
            if (verbose) println("Transition Ended")
            super.end()
        }

        override fun isFinished(): Boolean = true
    }.apply { bind() }

    fun run() {
        //Initialize our screens
        val startScreen = Screen(name = "Start", clawApp = clawApplication)
        val basicScreen = Screen(name = "Basic", clawApp = clawApplication)
        val twoWayScreen = Screen(name = "TwoWay", clawApp = clawApplication)
        val backScreen = Screen(name = "Back", clawApp = clawApplication)
        val finalScreen = Screen(name = "Final", clawApp = clawApplication)

        //Initialize our Items
        //We create a lever that we can change to true at any time
        //We also make a button conditional on whether the lever was flipped
        val basicLever: Item = object : Item(x1 = 20, y1 = 20, x2 = 30, y2 = 30, z = 1) {
            override fun useItem(){
                this.state = true
            }
        }.apply { state = false }

        val conditionalButton: Item = object : Item(x1 = 40, y1 = 40, x2 = 60, y2 = 60, z = 1,
            preconditions = listOf<Event>(object : Event() { override fun conditionsMet(): Boolean { return basicLever.state == true } }))
        {
            override fun useItem() {
                backScreen.addPortal(portal = Portal(destination = finalScreen, x1 = 20, y1 = 20, x2 = 80, y2 = 80, z = 1))
                this.state = "Pressed"
            }
        }.apply { state = "Not Pressed" }

        //Fill our screens with objects (Portals and Items)
        startScreen.apply {
            addPortal(portal = Portal(destination = basicScreen, x1 = 0, y1 = 0, x2 = 100, y2 = 100, z = 0))
        }

        basicScreen.apply {
            addPortal(portal = Portal(destination = twoWayScreen, x1 = 20, y1 = 20, x2 = 80, y2 = 80, z = 0))
            addItem(basicLever)
        }

        twoWayScreen.apply {
            addPortal(portal = Portal(destination = basicScreen, x1 = 0, y1 = 0, x2 = 49, y2 = 100, z = 0))
            addPortal(portal = Portal(destination = backScreen, x1 = 50, y1 = 0, x2 = 100, y2 = 100, z = 0))
        }

        backScreen.apply {
            addPortal(portal = Portal(destination = twoWayScreen, x1 = 20, y1 = 20, x2 = 80, y2 = 80, z = 0))
            addItem(item = conditionalButton)
        }

        clawApplication.apply {
            addScreen(screen = startScreen)
            addScreen(screen = basicScreen)
            addScreen(screen = twoWayScreen)
            addScreen(screen = backScreen)
        }

        clawApplication.currentScreen = startScreen

        if (verbose) println(clawApplication) //Start

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 0, y = 0)
        if (verbose) println(clawApplication) //Basic

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 0, y = 0)
        if (verbose) println(clawApplication) //Basic
        if (verbose) println("Lever flipped: ${basicLever.state}") //false

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 50, y = 50)
        if (verbose) println(clawApplication) //Two Way

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 75, y = 50)
        if (verbose) println(clawApplication) //Back

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 50, y = 50)
        if (verbose) println(clawApplication) //Two Way
        if (verbose) println("Condition Button: ${conditionalButton.state}") //Not Pressed

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 30, y = 30)
        if (verbose) println(clawApplication) //Two Way

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 25, y = 50)
        if (verbose) println(clawApplication) //Basic

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 25, y = 25)
        if (verbose) println(clawApplication) //Basic
        if (verbose) println("Lever flipped: ${basicLever.state}") //true

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 50, y = 50)
        if (verbose) println(clawApplication) //Two Way

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 75, y = 50)
        if (verbose) println(clawApplication) //Back

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 50, y = 50)
        if (verbose) println(clawApplication) //Back
        if (verbose) println("Condition Button: ${conditionalButton.state}") //Pressed

        clawApplication.update(deltaTime = 0.0)
        if (verbose) println()

        clawApplication.handleClick(x = 20, y = 20)
        if (verbose) println(clawApplication) //Final
    }
}