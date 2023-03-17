package entity.pieces

import entity.Square
import entity.helper.Color

interface Piece {
    var inGame: Boolean
    var square: Square
    val color: Color
    var visibleSquares: ArrayList<Square>
    var reachableSquares: ArrayList<Square>

    fun setPosition(value: Square) {
        this.square = value
    }
}