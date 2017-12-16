package players

interface PlayerUI {
    fun representMove(move:Int,onDecided:()->Unit)
}