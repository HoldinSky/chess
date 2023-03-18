package entity.pieces

import entity.board.Square
import entity.helper.Color

class Queen(override val color: Color) : Piece {
    override var inGame: Boolean = true
    override lateinit var square: Square
    override var possibleMoves: ArrayList<Square> = arrayListOf()

    constructor(color: Color, square: Square) : this(color) {
        this.square = square
    }

    override fun setPosition(value: Square) {
        this.square = value
        updatePossibleMoves()
    }

    private fun updatePossibleMoves() {
        possibleMoves = arrayListOf()
        val pairs = calculateQueenPossibleMoves(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "q"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

private fun calculateQueenPossibleMoves(file: Char, rank: Int): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    visibleSquares.addAll(calculateRookPossibleMoves(file, rank))
//    visibleSquares.addAll(calculateBishopPossibleMoves(file, rank))

    return visibleSquares
}

fun main() {
    println("${calculateQueenPossibleMoves('g', 4)}")
}