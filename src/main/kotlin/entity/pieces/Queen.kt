package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color

class Queen(override val color: Color) : Piece {
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
        val pairs = calculateQueenPossibleMoves(square.getFile(), square.getRank(), color, board)
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
        }
    }

    override fun toString(): String {
        var string = "q"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

private fun calculateQueenPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard): List<Pair<Char, Int>> {
    val visibleSquares = mutableListOf<Pair<Char, Int>>()

    visibleSquares.addAll(calculateRookPossibleMoves(file, rank, color, board))
    visibleSquares.addAll(calculateBishopPossibleMoves(file, rank, color, board))

    return visibleSquares
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/8/8/2Qb4/8/PPPPPPPP/RNBQKBNR")
    println("${calculateQueenPossibleMoves('d', 5, Color.WHITE, chessBoard)}")
    chessBoard.printBoard()
}