package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color
import entity.helper.squaresToPairs
import service.DEFAULT_POSITION

class Rook(override val color: Color) : Piece {
    override var inGame: Boolean = true
    override lateinit var square: Square
    override val possibleMoves: ArrayList<Square> = arrayListOf()
    override val squaresUnderAttack: ArrayList<Square> = arrayListOf()

    constructor(color: Color, square: Square) : this(color) {
        this.square = square
    }

    override fun setPosition(value: Square) {
        this.square = value
    }

    override fun updateMoves() {
        calculateRookPossibleMoves(square.getFile(), square.getRank(), color, square.getBoard())
    }

    private fun calculateRookPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard) {
        possibleMoves.clear()
        squaresUnderAttack.clear()

        calculateStraightMoves(file, rank, color, board, possibleMoves)
        calculateStraightAttacks(file, rank, board, squaresUnderAttack)
    }

    override fun toString(): String {
        var string = "r"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

fun main() {
    val chessBoard = ChessBoard(DEFAULT_POSITION)
    println("Possible moves of rook: ${squaresToPairs(chessBoard.getSquare('a', 1).getPiece()!!.possibleMoves)}")
    println("Squares under attack of rook: ${squaresToPairs(chessBoard.getSquare('a', 1).getPiece()!!.squaresUnderAttack)}")
    chessBoard.printBoard()
}