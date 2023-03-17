package entity.pieces

import entity.Square
import entity.helper.Color

class King(override val color: Color) : Piece {
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
        val pairs = calculateKingVisibleSquares(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            visibleSquares.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "k"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

private fun calculateKingVisibleSquares(file: Char, rank: Int): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    val moves = arrayOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, +1),
        Pair(0, +1),
        Pair(+1, +1),
        Pair(+1, 0),
        Pair(+1, -1),
        Pair(0, -1),
    )

    for (pair in moves) {
        val newFile = file + pair.first
        val newRank = rank + pair.second

        if (newFile in 'a'..'h' && newRank in 1..8) {
            visibleSquares.add(Pair(newFile, newRank))
        }
    }

    return visibleSquares
}

fun main() {
    println("${calculateKingVisibleSquares('e', 2)}")
}