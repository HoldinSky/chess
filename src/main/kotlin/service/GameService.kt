package service

import entity.board.ChessBoard
import entity.board.Move

const val DEFAULT_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"

class GameService {
    private val board: ChessBoard = ChessBoard(DEFAULT_POSITION)
    private var whiteToMove: Boolean = true
    private val moveHistory: ArrayList<Move> = arrayListOf()

    init {
        board.printBoard()
        printInfo()
    }

    private fun printInfo() {
        if (whiteToMove)
            println("\n***** White to move *****")
        else
            println("\n***** Black to move *****")

        println("===== Move  history =====")
        for (move in moveHistory) {
            println(move)
        }
    }
}