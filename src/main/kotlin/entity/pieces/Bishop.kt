package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color

class Bishop(override val color: Color) : Piece {
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
        val board = square.getBoard()
        val pairs = calculateBishopPossibleMoves(square.getFile(), square.getRank(), color,  board)
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
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

internal fun calculateBishopPossibleMoves(file: Char, rank: Int, color: Color,  board: ChessBoard): List<Pair<Char, Int>> {
    val possibleMoves = mutableListOf<Pair<Char, Int>>()
    val oppositeColor = if (color == Color.WHITE)
        Color.BLACK
    else
        Color.WHITE

    // beam to low left corner
    possibleMoves.addAll(checkDirection(file, rank, -1, -1, color, oppositeColor, board))
    // to high left corner
    possibleMoves.addAll(checkDirection(file, rank, -1, 1, color, oppositeColor, board))
    // to high right corner
    possibleMoves.addAll(checkDirection(file, rank, 1, 1, color, oppositeColor, board))
    // to low right corner
    possibleMoves.addAll(checkDirection(file, rank, 1, -1, color, oppositeColor, board))

    return possibleMoves
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/8/3n4/8/8/PPPPPPPP/RNBQKBNR")
    println("${calculateBishopPossibleMoves('c', 4, Color.WHITE, chessBoard)}")
    chessBoard.printBoard()
}