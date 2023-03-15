package entity

const val SIZE = 8

class ChessBoard() {
    private val board: Array<CharArray>

    init {  // create playing board
        val board = arrayListOf<CharArray>()
        for (i in 1..SIZE) board.add(CharArray(8))

        this.board = board.toTypedArray()
        clearBoard()
    }

    constructor(position: String) : this() {
        createBoardWithPosition(position)
    }

    private fun createBoardWithPosition(position: String) {
        var rank = 0
        var file = 0

        for (ch in position) {
            if (ch == '/') continue

            // place a number of spaces between pieces in a rank
            if (ch.isDigit()) {
                val newFile = file + ch.digitToInt()
                file = if (newFile > 7) {
                    fillWithBlank(rank, file, newFile)
                    ++rank
                    0
                } else {
                    fillWithBlank(rank, file, newFile)
                    newFile
                }
                continue
            }

            // place a piece on a square if such is mentioned in position
            if (ch.isLowerCase()) {
                when (ch) {
                    'r' -> placePieceOnSquare('r', file, rank)
                    'n' -> placePieceOnSquare('n', file, rank)
                    'b' -> placePieceOnSquare('b', file, rank)
                    'q' -> placePieceOnSquare('q', file, rank)
                    'k' -> placePieceOnSquare('k', file, rank)
                    'p' -> placePieceOnSquare('p', file, rank)
                }
            } else {
                when (ch) {
                    'R' -> placePieceOnSquare('R', file, rank)
                    'N' -> placePieceOnSquare('N', file, rank)
                    'B' -> placePieceOnSquare('B', file, rank)
                    'Q' -> placePieceOnSquare('Q', file, rank)
                    'K' -> placePieceOnSquare('K', file, rank)
                    'P' -> placePieceOnSquare('P', file, rank)
                }
            }
            if (++file > 7) {
                ++rank
                file = 0
            }
        }
    }

    private fun placePieceOnSquare(piece: Char, file: Int, rank: Int) {
        board[rank][file] = piece
    }

    private fun clearBoard() {
        for (i in 0 until SIZE) {
            fillWithBlank(i, 0, SIZE)
        }
    }

    private fun fillWithBlank(rank: Int, startPos: Int, endPos: Int) {
        for (i in startPos until endPos) {
            board[rank][i] = ' '
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