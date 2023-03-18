package entity.pieces

import entity.board.Square
import entity.helper.Color

class Rook(override val color: Color) : Piece {
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
        val pairs = calculateRookPossibleMoves(square.getFile(), square.getRank())
        val board = square.getBoard()
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "r"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

internal fun calculateRookPossibleMoves(file: Char, rank: Int): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    var newFile = file - 1
    while (newFile in 'a'..'h') {
        visibleSquares.add(Pair(newFile, rank))
        --newFile
    }

    newFile = file + 1
    while (newFile in 'a'..'h') {
        visibleSquares.add(Pair(newFile, rank))
        ++newFile
    }

    var newRank = rank - 1
    while (newRank in 1..8) {
        visibleSquares.add(Pair(file, newRank))
        --newRank
    }

    newRank = rank + 1
    while (newRank in 1..8) {
        visibleSquares.add(Pair(file, newRank))
        ++newRank
    }

    return visibleSquares
}

fun main() {
    println("${calculateRookPossibleMoves('g', 4)}")
}