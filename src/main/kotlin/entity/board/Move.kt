package entity.board

class Move(private val turn: Int, private val info: String) {

    fun getTurn(): Int {
        return this.turn
    }

    fun getInfo(): String {
        return this.info
    }

    override fun toString(): String {
        return "$turn. $info"
    }
}
