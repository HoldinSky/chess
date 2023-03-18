package entity.pieces

import entity.board.Square
import entity.helper.Color

class Bishop(override val color: Color) : Piece {
    override lateinit var square: Square
    override var inGame: Boolean = true
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
        val pairs = calculateBishopVisibleSquares(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            visibleSquares.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "b"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

internal fun calculateBishopVisibleSquares(file: Char, rank: Int): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    // beam to low left corner
    var newFile = file - 1
    var newRank = rank - 1
    while (newFile in 'a'..'h' && newRank in 1..8) {
        visibleSquares.add(Pair(newFile, newRank))
        --newFile
        --newRank
    }

    // to high left corner
    newFile = file - 1
    newRank = rank + 1
    while (newFile in 'a'..'h' && newRank in 1..8) {
        visibleSquares.add(Pair(newFile, newRank))
        --newFile
        ++newRank
    }

    // to high right corner
    newFile = file + 1
    newRank = rank + 1
    while (newFile in 'a'..'h' && newRank in 1..8) {
        visibleSquares.add(Pair(newFile, newRank))
        ++newFile
        ++newRank
    }

    // to low right corner
    newFile = file + 1
    newRank = rank - 1
    while (newFile in 'a'..'h' && newRank in 1..8) {
        visibleSquares.add(Pair(newFile, newRank))
        ++newFile
        --newRank
    }

    return visibleSquares
}

fun main() {
    println("${calculateBishopVisibleSquares('c', 2)}")
}