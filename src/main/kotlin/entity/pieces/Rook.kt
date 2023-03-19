package entity.pieces

import entity.board.ChessBoard
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
        val board = square.getBoard()
        val pairs = calculateRookPossibleMoves(square.getFile(), square.getRank(), color, board)
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

internal fun calculateRookPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard): List<Pair<Char, Int>> {
    val possibleMoves = mutableListOf<Pair<Char, Int>>()
    val oppositeColor = if (color == Color.WHITE)
        Color.BLACK
    else
        Color.WHITE

    // beam to the left
    possibleMoves.addAll(checkDirection(file, rank, -1, 0, color, oppositeColor, board))
    // to the top
    possibleMoves.addAll(checkDirection(file, rank, 0, 1, color, oppositeColor, board))
    // to the right
    possibleMoves.addAll(checkDirection(file, rank, 1, 0, color, oppositeColor, board))
    // to the bottom
    possibleMoves.addAll(checkDirection(file, rank, 0, -1, color, oppositeColor, board))

    return possibleMoves
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/8/8/2QB4/8/PPPPPPPP/RNBQKBNR")
    println("${calculateRookPossibleMoves('e', 5, Color.BLACK, chessBoard)}")
    chessBoard.printBoard()
}