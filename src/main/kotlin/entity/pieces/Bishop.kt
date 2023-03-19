package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color
import entity.helper.squaresToPairs
import service.DEFAULT_POSITION

class Bishop(override val color: Color) : Piece {
    override lateinit var square: Square
    override var inGame: Boolean = true
    override val possibleMoves: ArrayList<Square> = arrayListOf()
    override val squaresUnderAttack: ArrayList<Square> = arrayListOf()

    constructor(color: Color, square: Square) : this(color) {
        this.square = square
    }

    override fun setPosition(value: Square) {
        this.square = value
    }

    override fun updateMoves() {
        calculateBishopMoves(square.getFile(), square.getRank(), color, square.getBoard())
    }

    private fun calculateBishopMoves(file: Char, rank: Int, color: Color, board: ChessBoard) {

        possibleMoves.clear()
        squaresUnderAttack.clear()

        calculateDiagonalMoves(file, rank, color, board, possibleMoves)
        calculateDiagonalAttacks(file, rank, board, squaresUnderAttack)
    }

    override fun toString(): String {
        var string = "b"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

fun main() {
    val chessBoard = ChessBoard(DEFAULT_POSITION)
    println("Possible moves of bishop: ${squaresToPairs(chessBoard.getSquare('c', 1).getPiece()!!.possibleMoves)}")
    println("Squares under attack of bishop: ${squaresToPairs(chessBoard.getSquare('c', 1).getPiece()!!.squaresUnderAttack)}")
    chessBoard.printBoard()
}