class Screen(val name: String = "", val clawApp: ClawApplication, val associations: Any? = null) {
    var interactables = listOf<Interactable>()

    fun addPortal(portal: Portal) {
        interactables = (interactables + portal).sortedByDescending { it.z }
    }

    fun addItem(item: Item) {
        interactables = (interactables + item).sortedByDescending { it.z }
    }
}