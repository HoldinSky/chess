package entity.pieces

import entity.Square
import entity.helper.Color

interface Piece {
    var inGame: Boolean
    val square: Square
    val color: Color
    val reachableSquares: ArrayList<Square>
}