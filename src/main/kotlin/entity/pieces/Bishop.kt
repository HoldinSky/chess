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
        val pairs = calculateBishopPossibleMoves(square.getFile(), square.getRank(), board, color)
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

internal fun calculateBishopPossibleMoves(file: Char, rank: Int, board: ChessBoard, color: Color): List<Pair<Char, Int>> {
    val possibleMoves = mutableListOf<Pair<Char, Int>>()
    val oppositeColor = if (color == Color.WHITE)
        Color.BLACK
    else
        Color.WHITE

    // beam to low left corner
    possibleMoves.addAll(checkVisibleLines(file - 1, rank - 1, -1, -1, board, color, oppositeColor))
    // to high left corner
    possibleMoves.addAll(checkVisibleLines(file - 1, rank + 1, -1, 1, board, color, oppositeColor))
    // to high right corner
    possibleMoves.addAll(checkVisibleLines(file + 1, rank + 1, 1, 1, board, color, oppositeColor))
    // to low right corner
    possibleMoves.addAll(checkVisibleLines(file + 1, rank - 1, 1, -1, board, color, oppositeColor))

    return possibleMoves
}

// function to add possible moves in one direction
private fun checkVisibleLines(
    file: Char, rank: Int, deltaFile: Int, deltaRank: Int,
    board: ChessBoard, color: Color, oppositeColor: Color
): List<Pair<Char, Int>> {
    val list = mutableListOf<Pair<Char, Int>>()
    var f = file
    var r = rank

    while (f in 'a'..'h' && r in 1..8) {
        if (board.getSquare(f, r).getPiece()?.color == oppositeColor) {
            list.add(Pair(f, r))
            break
        } else if (board.getSquare(f, r).getPiece()?.color == color) {
            break
        }
        list.add(Pair(f, r))
        f += deltaFile
        r += deltaRank
    }

    return list
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/8/3n4/8/8/PPPPPPPP/RNBQKBNR")
    println("${calculateBishopPossibleMoves('c', 4, chessBoard, Color.WHITE)}")
    chessBoard.printBoard()
}