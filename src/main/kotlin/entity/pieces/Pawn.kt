package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color

class Pawn(
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
        val pairs = calculatePawnPossibleMoves(square.getFile(), square.getRank(), color, board)
        for (pair in pairs) {
            possibleMoves.add(board.getSquare(pair.first, pair.second))
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

private fun calculatePawnPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard): List<Pair<Char, Int>> {
    val possibleMoves = arrayListOf<Pair<Char, Int>>()

    val straights = calculatePawnStraights(rank, color)
    val diagonals = calculatePawnDiagonals(color)

    for (move in straights) {
        val newFile = file + move.first
        val newRank = rank + move.second

        if (newFile in 'a'..'h' && newRank in 1..8 && board.getSquare(newFile, newRank).isBlank())
            possibleMoves.add(Pair(newFile, newRank))
    }

    for (movement in diagonals) {
        val newFile = file + movement.first
        val newRank = rank + movement.second

        // Check if move is within the board boundaries and there are no same color pieces
        if (newFile in 'a'..'h' && newRank in 1..8) {
            val square = board.getSquare(newFile, newRank)
            if (!square.isBlank() && square.getPiece()?.color != color)
                possibleMoves.add(Pair(newFile, newRank))
        }
    }

    return possibleMoves
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/2N1Q3/8/8/8/PPPPPPPP/RNBQKBNR")
    println("${calculatePawnPossibleMoves('d', 7, Color.BLACK, chessBoard)}")
    chessBoard.printBoard()
}