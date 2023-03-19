package entity.pieces

import entity.board.Square
import entity.helper.Color

interface Piece {
    var inGame: Boolean
    var square: Square
    val color: Color
    val possibleMoves: ArrayList<Square>
    val squaresUnderAttack: ArrayList<Square>

    fun setPosition(value: Square) {
        this.square = value
    }

    fun getPosition(): Square {
        return this.square
    }

    fun updateMoves()
}