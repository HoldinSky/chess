package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color
import entity.helper.squaresToPairs
import service.DEFAULT_POSITION

class King(override val color: Color) : Piece {
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
        calculateKingMoves(square.getFile(), square.getRank(), square.getBoard())
    }

    private fun calculateKingMoves(file: Char, rank: Int, board: ChessBoard) {
        this.possibleMoves.clear()
        this.squaresUnderAttack.clear()

        val moves = arrayOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, +1),
            Pair(0, +1),
            Pair(+1, +1),
            Pair(+1, 0),
            Pair(+1, -1),
            Pair(0, -1),
        )

        for (pair in moves) {
            val newFile = file + pair.first
            val newRank = rank + pair.second

            if (newFile in 'a'..'h' && newRank in 1..8) {
                val square = board.getSquare(newFile, newRank)
                if (square.isBlank() || (!square.isBlank() && square.getPiece()!!.color != color)) {
                    this.possibleMoves.add(square)
                    this.squaresUnderAttack.add(square)
                }
            }
        }
    }

    override fun toString(): String {
        var string = "k"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

fun main() {
    val chessBoard = ChessBoard(DEFAULT_POSITION)
    println("Possible moves of king: ${squaresToPairs(chessBoard.getSquare('e', 1).getPiece()!!.possibleMoves)}")
    println("Squares under attack of king: ${squaresToPairs(chessBoard.getSquare('e', 1).getPiece()!!.squaresUnderAttack)}")
    chessBoard.printBoard()
}