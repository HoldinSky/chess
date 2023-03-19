package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color
import entity.helper.squaresToPairs
import service.DEFAULT_POSITION

class Pawn(
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
        calculatePawnPossibleMoves(square.getFile(), square.getRank(), color, square.getBoard())
    }

    private fun calculatePawnPossibleMoves(file: Char, rank: Int, color: Color, board: ChessBoard) {
        possibleMoves.clear()
        squaresUnderAttack.clear()

        for (sq in calculatePawnDiagonals(file, rank, color, board)) {  // calculating diagonals
            if (sq.getPiece()?.color != color)
                possibleMoves.add(sq)
            squaresUnderAttack.add(sq)
        }

        possibleMoves.addAll(calculatePawnStraights(file, rank, color, board))
    }

    override fun toString(): String {
        var string = "p"
        if (color == Color.WHITE) {
            string = string.uppercase()
        }
        return string
    }
}

private fun calculatePawnDiagonals(file: Char, rank: Int, color: Color, board: ChessBoard): List<Square> {
    val list = mutableListOf<Square>()

    if (color == Color.WHITE) {
        if (file - 1 >= 'a')
            list.add(board.getSquare(file - 1, rank + 1))
        if (file + 1 <= 'h')
            list.add(board.getSquare(file + 1, rank + 1))
        return list
    }
    if (file - 1 >= 'a')
        list.add(board.getSquare(file - 1, rank - 1))
    if (file + 1 <= 'h')
        list.add(board.getSquare(file + 1, rank - 1))
    return list
}

private fun calculatePawnStraights(file: Char, rank: Int, color: Color, board: ChessBoard): List<Square> {
    val list = mutableListOf<Square>()

    var square: Square
    if (color == Color.WHITE) {
        square = board.getSquare(file, rank + 1)
        if (square.isBlank())
            list.add(square)
        else
            return list

        if (rank == 2) {
            square = board.getSquare(file, rank + 2)
            if (square.isBlank())
                list.add(square)
            else
                return list
        }
        return list
    }
    square = board.getSquare(file, rank - 1)
    if (square.isBlank())
        list.add(square)
    else
        return list

    if (rank == 7) {
        square = board.getSquare(file, rank - 2)
        if (square.isBlank())
            list.add(square)
        else
            return list
    }
    return list
}

fun main() {
    val chessBoard = ChessBoard("rnbqkbnr/pppppppp/8/8/2n1q3/3P4/PPPPPPPP/RNBQKBNR")
    println("Possible moves of pawn: ${squaresToPairs(chessBoard.getSquare('d', 3).getPiece()!!.possibleMoves)}")
    println(
        "Squares under attack of pawn: ${
            squaresToPairs(
                chessBoard.getSquare('d', 3).getPiece()!!.squaresUnderAttack
            )
        }"
    )
    chessBoard.printBoard()
}