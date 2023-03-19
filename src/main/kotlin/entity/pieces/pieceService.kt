package entity.pieces

import entity.board.ChessBoard
import entity.board.Square
import entity.helper.Color

internal fun checkMoves(
    file: Char, rank: Int, deltaFile: Int, deltaRank: Int,
    color: Color, oppositeColor: Color, board: ChessBoard
): List<Square> {
    val list = mutableListOf<Square>()
    var f = file + deltaFile
    var r = rank + deltaRank

    while (f in 'a'..'h' && r in 1..8) {
        val square = board.getSquare(f, r)
        if (square.getPiece()?.color == oppositeColor) {
            list.add(square)
            break
        }
        if (square.getPiece()?.color == color)
            break

        list.add(square)
        f += deltaFile
        r += deltaRank
    }

    return list
}

internal fun checkAttacks(
    file: Char, rank: Int, deltaFile: Int, deltaRank: Int, board: ChessBoard
): List<Square> {
    val list = mutableListOf<Square>()
    var f = file + deltaFile
    var r = rank + deltaRank

    while (f in 'a'..'h' && r in 1..8) {
        val square = board.getSquare(f, r)
        list.add(square)
        if (!square.isBlank())
            break
        f += deltaFile
        r += deltaRank
    }

    return list
}

internal fun calculateDiagonalMoves(
    file: Char, rank: Int, color: Color, board: ChessBoard, container: ArrayList<Square>
) {
    val oppositeColor = if (color == Color.WHITE)
        Color.BLACK
    else
        Color.WHITE

    // beam to low left corner
    container.addAll(checkMoves(file, rank, -1, -1, color, oppositeColor, board))
    // to high left corner
    container.addAll(checkMoves(file, rank, -1, 1, color, oppositeColor, board))
    // to high right corner
    container.addAll(checkMoves(file, rank, 1, 1, color, oppositeColor, board))
    // to low right corner
    container.addAll(checkMoves(file, rank, 1, -1, color, oppositeColor, board))
}

internal fun calculateDiagonalAttacks(
    file: Char, rank: Int, board: ChessBoard, container: ArrayList<Square>
) {
    container.addAll(checkAttacks(file , rank, -1, -1, board))
    container.addAll(checkAttacks(file , rank, -1, 1, board))
    container.addAll(checkAttacks(file , rank, 1, 1, board))
    container.addAll(checkAttacks(file , rank, 1, -1, board))
}

internal fun calculateStraightMoves(
    file: Char, rank: Int, color: Color, board: ChessBoard, container: ArrayList<Square>
) {
    val oppositeColor = if (color == Color.WHITE)
        Color.BLACK
    else
        Color.WHITE

    // beam to the left
    container.addAll(checkMoves(file, rank, -1, 0, color, oppositeColor, board))
    // to the top
    container.addAll(checkMoves(file, rank, 0, 1, color, oppositeColor, board))
    // to the right
    container.addAll(checkMoves(file, rank, 1, 0, color, oppositeColor, board))
    // to the bottom
    container.addAll(checkMoves(file, rank, 0, -1, color, oppositeColor, board))
}

internal fun calculateStraightAttacks(
    file: Char, rank: Int, board: ChessBoard, container: ArrayList<Square>
) {
    // beam to the left
    container.addAll(checkAttacks(file, rank, -1, 0, board))
    // to the top
    container.addAll(checkAttacks(file, rank, 0, 1, board))
    // to the right
    container.addAll(checkAttacks(file, rank, 1, 0, board))
    // to the bottom
    container.addAll(checkAttacks(file, rank, 0, -1, board))
}