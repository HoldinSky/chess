package entity.pieces

import entity.board.Square
import entity.helper.Color

class Knight(
    override val color: Color
) : Piece {
    override lateinit var square: Square
    override var inGame: Boolean = true
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
        val pairs = calculateKnightPossibleMoves(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "n"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

private fun calculateKnightPossibleMoves(file: Char, rank: Int): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    val movements = arrayOf(
        Pair(2, 1),
        Pair(2, -1),
        Pair(-2, 1),
        Pair(-2, -1),
        Pair(1, 2),
        Pair(1, -2),
        Pair(-1, 2),
        Pair(-1, -2)
    )

    val fileIndex = file.uppercaseChar().code - 65

    for (movement in movements) {
        val newFile = fileIndex + movement.first
        val newRank = rank + movement.second

        // Check if move is within the board boundaries
        if (newFile in 0..7 && newRank in 1..8) {
            visibleSquares.add(Pair((newFile + 65).toChar(), newRank))
        }
    }

    return visibleSquares
}

fun main() {
    println("${calculateKnightPossibleMoves('d', 5)}")
}