import entity.ChessBoard

const val DEFAULT_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"

fun main() {
    val board = ChessBoard(DEFAULT_POSITION)
    board.printBoard()
}