package entity

import entity.helper.Color
import service.createPiece

const val SIZE = 8

class ChessBoard() {
    private val board: Array<Array<Square>>

    init {  // create playing board
        val board = arrayListOf<Array<Square>>()

        for (i in 1..SIZE) {    // going through ranks from 8 to 1
            val rank = arrayListOf<Square>()
            for (j in 1..SIZE) {    // going through files
                rank.add(Square(this, (j + 96).toChar(), i))
            }
            board.add(rank.toTypedArray())
        }

        this.board = board.toTypedArray()
        clearBoard()
    }

    constructor(position: String) : this() {
        createBoardWithPosition(position)
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
            placePieceOnSquare(ch, file, rank)
            if (++file >= 'h') {
                ++rank
                file = 'a'
            }
        }
    }

    fun getSquare(file: Char, rank: Int): Square {   // takes as input file and rank counting from 1
        val fileIndex = file.uppercase().toInt() - 65
        return this.board[rank - 1][fileIndex]
    }

    private fun placePieceOnSquare(type: Char, file: Char, rank: Int) {
        val color =
            if (type.isLowerCase()) Color.BLACK
            else Color.WHITE

        val fileIndex = file.uppercase().toInt() - 65
        val piece = createPiece(type, color, board[rank - 1][fileIndex])
        if (piece != null)
            board[rank - 1][fileIndex].setPiece(piece)
    }

    private fun clearBoard() {
        for (i in 0 until SIZE) {
            fillWithBlank(i, 'a', 'h')
        }
    }

    private fun fillWithBlank(rank: Int, startPos: Char, endPos: Char) {
        for (file in startPos until endPos) {
            val index = file.uppercase().toInt() - 65
            board[rank][index].removePiece()
        }
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
