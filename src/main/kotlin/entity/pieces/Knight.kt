package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color
import entity.helper.squaresToPairs
import service.DEFAULT_POSITION

class Knight(
    override val color: Color
) : Piece {
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
        calculateKnightPossibleMoves(square.getFile(), square.getRank(), color, square.getBoard())
    }

    private fun calculateKnightPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard) {
        possibleMoves.clear()
        squaresUnderAttack.clear()

        val movements = listOf(
            Pair(2, 1),
            Pair(2, -1),
            Pair(-2, 1),
            Pair(-2, -1),
            Pair(1, 2),
            Pair(1, -2),
            Pair(-1, 2),
            Pair(-1, -2)
        )

        for (movement in movements) {
            val newFile = file + movement.first
            val newRank = rank + movement.second

            // Check if move is within the board boundaries and there are no same color pieces
            if (newFile in 'a'..'h' && newRank in 1..8) {
                val square = board.getSquare(newFile, newRank)
                if (square.getPiece()?.color != color)
                    possibleMoves.add(square)
                squaresUnderAttack.add(square)
            }
        }
    }

    override fun toString(): String {
        var string = "n"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

fun main() {
    val chessBoard = ChessBoard(DEFAULT_POSITION)
    println("Possible moves of knight: ${squaresToPairs(chessBoard.getSquare('b', 1).getPiece()!!.possibleMoves)}")
    println("Squares under attack of knight: ${squaresToPairs(chessBoard.getSquare('b', 1).getPiece()!!.squaresUnderAttack)}")
    chessBoard.printBoard()
}