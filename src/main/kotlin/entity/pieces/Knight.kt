package entity.pieces

import entity.Square
import entity.helper.Color

class Knight(
    override val color: Color
) : Piece {
    override lateinit var square: Square
    override var inGame: Boolean = true
    override var reachableSquares: ArrayList<Square> = arrayListOf()

    constructor(color: Color, square: Square, inGame: Boolean) : this(color) {
        this.square = square
        this.inGame = inGame
    }

    fun setSquare(value: Square) {
        this.square = value

        val pairs = calculateReachableSquares(square.getFile(), square.getRank())
        val board = square.getBoard()
        reachableSquares = arrayListOf()
        for (pair in pairs) {
            reachableSquares.add(board.getSquare(pair.first, pair.second))
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

fun calculateReachableSquares(file: Char, rank: Int): List<Pair<Char, Int>> {
    val reachableSquares = mutableListOf<Pair<Char, Int>>()

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

    val fileIndex = file.uppercase().toInt() - 65

    for (movement in movements) {
        val newFile = fileIndex + movement.first
        val newRank = rank + movement.second

        // Check if move is within the board boundaries
        if (newFile in 0..7 && newRank in 1..8) {
            reachableSquares.add(Pair((newFile + 65).toChar(), newRank))
        }
    }

    return reachableSquares
}