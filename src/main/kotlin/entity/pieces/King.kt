package entity.pieces

import entity.board.Square
import entity.helper.Color

class King(override val color: Color) : Piece {
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
        val pairs = calculateKingPossibleMoves(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
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

private fun calculateKingPossibleMoves(file: Char, rank: Int): List<Pair<Char, Int>> {
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
    println("${calculateKingPossibleMoves('e', 2)}")
}