abstract class Interactable(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    val z: Int
) {
    abstract fun onClick()

    fun boundsContain(x: Int, y: Int) : Boolean {
        return x in x1..x2 && y in y1..y2
    }
}

abstract class Item(
    var preconditions: List<Event> = listOf(),
    x1: Int,
    y1: Int,
    x2: Int,
    y2: Int,
    z: Int
) : Interactable(x1, y1, x2, y2, z) {

    var state: Any? = null

    override fun onClick() {
        if(preconditions.all { it.conditionsMet() }) {
            useItem()
        }
    }

    abstract fun useItem()
}

class Portal(
    val destination: Screen,
    x1: Int,
    y1: Int,
    x2: Int,
    y2: Int,
    z: Int
) : Interactable(x1, y1, x2, y2, z) {
    override fun onClick() {
        destination.clawApp.usePortal(portal = this)
    }
}

abstract class Event {
    abstract fun conditionsMet() : Boolean
}