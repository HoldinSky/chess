package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color

class Knight(
    override val color: Color
) : Piece {
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
        val pairs = calculateKnightPossibleMoves(square.getFile(), square.getRank(), color, board)
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
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

private fun calculateKnightPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard): List<Pair<Char, Int>> {
    val possibleMoves = mutableListOf<Pair<Char, Int>>()

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
        if (newFile in 'a'..'h' && newRank in 1..8 && board.getSquare(newFile, newRank).getPiece()?.color != color) {
            possibleMoves.add(Pair(newFile, newRank))
        }
    }

    return possibleMoves
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/8/8/2QB4/8/PPPPPPPP/RNBQKBNR")
    println("${calculateKnightPossibleMoves('e', 5, Color.WHITE, chessBoard)}")
    chessBoard.printBoard()
}