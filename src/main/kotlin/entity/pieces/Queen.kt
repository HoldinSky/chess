package entity.pieces

import entity.board.Square
import entity.helper.Color

class Queen(override val color: Color) : Piece {
    override var inGame: Boolean = true
    override lateinit var square: Square
    override var visibleSquares: ArrayList<Square> = arrayListOf()
    override var reachableSquares: ArrayList<Square> = arrayListOf()

    constructor(color: Color, square: Square) : this(color) {
        this.square = square
    }

    override fun setPosition(value: Square) {
        this.square = value
        updateVisibleSquares()
    }

    private fun updateVisibleSquares() {
        visibleSquares = arrayListOf()
        val pairs = calculateQueenVisibleSquares(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            visibleSquares.add(board.getSquare(pair.first, pair.second))
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

private fun calculateQueenVisibleSquares(file: Char, rank: Int): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    visibleSquares.addAll(calculateRookVisibleSquares(file, rank))
    visibleSquares.addAll(calculateBishopVisibleSquares(file, rank))

    return visibleSquares
}

fun main() {
    println("${calculateQueenVisibleSquares('g', 4)}")
}