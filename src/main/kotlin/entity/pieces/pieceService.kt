package entity.pieces

import entity.board.ChessBoard
import entity.helper.Color

internal fun checkDirection(
    file: Char, rank: Int, deltaFile: Int, deltaRank: Int,
    color: Color, oppositeColor: Color, board: ChessBoard
): List<Pair<Char, Int>> {
    val list = mutableListOf<Pair<Char, Int>>()
    var f = file + deltaFile
    var r = rank + deltaRank

    while (f in 'a'..'h' && r in 1..8) {
        if (board.getSquare(f, r).getPiece()?.color == oppositeColor) {
            list.add(Pair(f, r))
            break
        }
        if (board.getSquare(f, r).getPiece()?.color == color)
            break

        list.add(Pair(f, r))
        f += deltaFile
        r += deltaRank
    }

    return list
}

internal fun calculatePawnStraights(rank: Int, color: Color): List<Pair<Int, Int>> {
    val movements = mutableListOf<Pair<Int, Int>>()

    if (color == Color.WHITE) {
        movements.add(Pair(0, 1))
        if (rank == 2)
            movements.add(Pair(0, 2))
    } else {
        movements.add(Pair(0, -1))
        if (rank == 7)
            movements.add(Pair(0, -2))
    }

    return movements
}

internal fun calculatePawnDiagonals(color: Color): List<Pair<Int, Int>> {
    return if (color == Color.WHITE) {
        listOf(Pair(-1, 1), Pair(1, 1))
    } else {
        listOf(Pair(-1, -1), Pair(1, -1))
    }
}