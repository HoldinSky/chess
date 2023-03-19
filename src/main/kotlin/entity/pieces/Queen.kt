package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color
import entity.helper.squaresToPairs
import service.DEFAULT_POSITION

class Queen(override val color: Color) : Piece {
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
        calculateQueenPossibleMoves(square.getFile(), square.getRank(), color, square.getBoard())
    }

    private fun calculateQueenPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard) {
        possibleMoves.clear()
        squaresUnderAttack.clear()

        calculateDiagonalMoves(file, rank, color, board, possibleMoves)
        calculateStraightMoves(file, rank, color, board, possibleMoves)

        calculateDiagonalAttacks(file, rank, board, squaresUnderAttack)
        calculateStraightAttacks(file, rank, board, squaresUnderAttack)
    }

    override fun toString(): String {
        var string = "q"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

fun main() {
    val chessBoard = ChessBoard(DEFAULT_POSITION)
    println("Possible moves of queen: ${squaresToPairs(chessBoard.getSquare('d', 1).getPiece()!!.possibleMoves)}")
    println("Squares under attack of queen: ${squaresToPairs(chessBoard.getSquare('d', 1).getPiece()!!.squaresUnderAttack)}")
    chessBoard.printBoard()
}