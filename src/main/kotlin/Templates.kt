class OneDirectionalScreen(name: String = "", clawApp: ClawApplication): Screen(name, clawApp)

class ToggleButton(preconditions: List<Event> = listOf(), x1: Int, y1: Int, x2: Int, y2: Int, z: Int):
    Item(x1 = x1, y1 = y1, x2 = x2, y2 = y2, z = z) {

    init {
        this.state = false
    }

    override fun useItem() {
        this.state = if(state is Boolean) !(state as Boolean) else true
    }
}

class OneTimeLever(preconditions: List<Event> = listOf(), x1: Int, y1: Int, x2: Int, y2: Int, z: Int):
    Item(x1 = x1, y1 = y1, x2 = x2, y2 = y2, z = z) {

    init {
        this.state = false
    }

    override fun useItem() {
        this.state = true
    }
}
