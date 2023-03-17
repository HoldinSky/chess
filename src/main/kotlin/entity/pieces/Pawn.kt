package entity.pieces

import entity.Square
import entity.helper.Color

class Pawn(
    override val color: Color
) : Piece {
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
        val board = square.getBoard()
        val pairs = calculateVisibleSquares(square.getFile(), square.getRank(), color)
        for (pair in pairs) {
            visibleSquares.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "p"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

private fun calculateVisibleSquares(file: Char, rank: Int, color: Color): List<Pair<Char, Int>> {
    val visibleSquares = arrayListOf<Pair<Char, Int>>()

    if (color == Color.WHITE) {
        visibleSquares.add(Pair(file, rank + 1))    // one square up
        if (rank == 2) {    // two squares up
            visibleSquares.add(Pair(file, rank + 2))
        }
        if (file - 1 in 'a'..'h')
            visibleSquares.add(Pair(file - 1, rank + 1))
        if (file + 1 in 'a'..'h')
            visibleSquares.add(Pair(file + 1, rank + 1))
        return visibleSquares
    }

    visibleSquares.add(Pair(file, rank - 1))    // one square down
    if (rank == 7) {        // two squares down
        visibleSquares.add(Pair(file, rank - 2))
    }
    if (file - 1 in 'a'..'h')
        visibleSquares.add(Pair(file - 1, rank - 1))
    if (file + 1 in 'a'..'h')
        visibleSquares.add(Pair(file + 1, rank - 1))


    return visibleSquares
}

fun main() {
    println("${calculateVisibleSquares('a', 6, Color.BLACK)}")
}