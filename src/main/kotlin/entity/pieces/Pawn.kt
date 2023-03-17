package entity.pieces

import entity.ChessBoard
import entity.Square
import entity.helper.Color

class Pawn(
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

        val board = square.getBoard()
        val pairs = calculateReachableSquares(square.getFile(), square.getRank(), color, board)
        reachableSquares = arrayListOf()
        for (pair in pairs) {
            reachableSquares.add(board.getSquare(pair.first, pair.second))
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

fun calculateReachableSquares(file: Char, rank: Int, color: Color, board: ChessBoard): List<Pair<Char, Int>> {
    val result = arrayListOf<Pair<Char, Int>>()
    if (color == Color.WHITE) {
        result.add(Pair(file, rank + 1))
        if (rank == 2) {
            result.add(Pair(file, rank + 2))
        }
        if (file == 'a') {  // calculate possible diagonals
            if (board.getSquare('b', rank + 1).getPiece() != null)
                result.add(Pair('b', rank + 1))
        } else if (file == 'h') {
            if (board.getSquare('g', rank + 1).getPiece() != null)
                result.add(Pair('g', rank + 1))
        } else {
            if (board.getSquare(file - 1, rank + 1).getPiece() != null)
                result.add(Pair(file - 1, rank + 1))
            if (board.getSquare(file + 1, rank + 1).getPiece() != null)
                result.add(Pair(file + 1, rank + 1))
        }
    } else {
        result.add(Pair(file, rank - 1))
        if (rank == 7) {
            result.add(Pair(file, rank - 2))
        }
        if (file == 'a') {
            if (board.getSquare('b', rank - 1).getPiece() != null)
                result.add(Pair('b', rank - 1))
        } else if (file == 'h') {
            if (board.getSquare('g', rank - 1).getPiece() != null)
                result.add(Pair('g', rank - 1))
        } else {
            if (board.getSquare(file - 1, rank - 1).getPiece() != null)
                result.add(Pair(file - 1, rank - 1))
            if (board.getSquare(file + 1, rank - 1).getPiece() != null)
                result.add(Pair(file + 1, rank - 1))
        }
    }
    return result
}