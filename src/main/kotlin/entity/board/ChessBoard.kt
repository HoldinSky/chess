package entity.board

import entity.helper.Color
import entity.pieces.Piece
import service.DEFAULT_POSITION
import service.createPiece

const val SIZE = 8

class ChessBoard() {
    private val board: Array<Array<Square>>
    private val whitePieces: ArrayList<Piece>
    private val blackPieces: ArrayList<Piece>
    private val squaresOnWhiteAttack: ArrayList<Square>
    private val squaresOnBlackAttack: ArrayList<Square>

    init {  // create playing board
        val board = arrayListOf<Array<Square>>()

        for (i in 1..SIZE) {    // going through ranks from 8 to 1
            val rank = arrayListOf<Square>()
            for (j in 1..SIZE) {    // going through files
                rank.add(Square(this, (j + 96).toChar(), SIZE - i + 1))
            }
            board.add(rank.toTypedArray())
        }

        this.board = board.toTypedArray()
        clearBoard()
        whitePieces = arrayListOf()
        blackPieces = arrayListOf()
        squaresOnWhiteAttack = arrayListOf()
        squaresOnBlackAttack = arrayListOf()
    }

    constructor(position: String) : this() {
        createBoardWithPosition(position)
        getCurrentMoves()
        calculateSquaresOnAttack()
    }

    private fun getCurrentMoves() {
        for (piece in whitePieces) {
            piece.updateMoves()
        }
        for (piece in blackPieces) {
            piece.updateMoves()
        }
    }

    private fun createBoardWithPosition(position: String) {
        var rank = 0
        var file = 'a'

        for (ch in position) {
            if (ch == '/') continue

            // place a number of spaces between pieces in a rank
            if (ch.isDigit()) {
                val newFile = file + ch.digitToInt()
                file = if (newFile >= 'h') {
                    fillWithBlank(rank, file, newFile)
                    ++rank
                    'a'
                } else {
                    fillWithBlank(rank, file, newFile)
                    newFile
                }
                continue
            }

            // place a piece on a square if such is mentioned in position
            val piece = placePieceOnSquare(ch, file, rank)
            if (piece != null) {
                if (piece.color == Color.WHITE)
                    whitePieces.add(piece)
                else
                    blackPieces.add(piece)
            }
            if (++file > 'h') {
                ++rank
                file = 'a'
            }
        }
    }

    fun getSquare(file: Char, rank: Int): Square {   // takes as input file 'a'..'h' and rank 1..8
        val fileIndex = file.uppercaseChar().code - 65
        val arrayRank = SIZE - rank
        return this.board[arrayRank][fileIndex]
    }

    private fun placePieceOnSquare(type: Char, file: Char, rank: Int): Piece? {
        val color =
            if (type.isLowerCase()) Color.BLACK
            else Color.WHITE

        val fileIndex = file.uppercaseChar().code - 65
        val piece = createPiece(type, color, board[rank][fileIndex])
        if (piece != null)
            board[rank][fileIndex].setPiece(piece)
        return piece
    }

    private fun clearBoard() {
        for (i in 0 until SIZE) {
            fillWithBlank(i, 'a', 'h')
        }
    }

    private fun fillWithBlank(rank: Int, startPos: Char, endPos: Char) {
        for (file in startPos until endPos) {
            val index = file.uppercaseChar().code - 65
            board[rank][index].removePiece()
        }
    }

    private fun calculateSquaresOnAttack() {
        squaresOnWhiteAttack.clear()
        squaresOnBlackAttack.clear()
        for (piece in whitePieces) {
            for (square in piece.squaresUnderAttack) {
                if (!squaresOnWhiteAttack.contains(square))
                    squaresOnWhiteAttack.add(square)
            }
        }
        for (piece in blackPieces) {
            for (square in piece.squaresUnderAttack) {
                if (!squaresOnBlackAttack.contains(square))
                    squaresOnBlackAttack.add(square)
            }
        }
    }

    fun getWhiteAttacks(): ArrayList<Square> {
        return this.squaresOnWhiteAttack
    }

    fun getBlackAttacks(): ArrayList<Square> {
        return this.squaresOnBlackAttack
    }

    fun printBoard() {
        for (i in 0 until SIZE) {
            print("${SIZE - i} | ")
            for (j in 0 until SIZE) {
                print("${board[i][j]}  ")
            }
            println()
        }
        println("    ----------------------")
        println("    a  b  c  d  e  f  g  h")
    }

}

fun main() {
    val board = ChessBoard(DEFAULT_POSITION)
    val whiteAttack = arrayListOf<Pair<Char, Int>>()
    val blackAttack = arrayListOf<Pair<Char, Int>>()

    for (square in board.getWhiteAttacks()) {
        whiteAttack.add(Pair(square.getFile(), square.getRank()))
    }
    for (square in board.getBlackAttacks()) {
        blackAttack.add(Pair(square.getFile(), square.getRank()))
    }
    println("Squares that white pieces attack: $whiteAttack")
    println("Squares that black pieces attack: $blackAttack")
    board.printBoard()
}