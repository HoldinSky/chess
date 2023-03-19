package service

import entity.board.Square
import entity.helper.Color
import entity.pieces.*

fun createPiece(type: Char, color: Color): Piece? {
    val piece: Piece? = when (type.lowercaseChar()) {
        'p' -> Pawn(color)
        'n' -> Knight(color)
        'b' -> Bishop(color)
        'r' -> Rook(color)
        'q' -> Queen(color)
        'k' -> King(color)
        else -> null
    }

    return piece
}

fun createPiece(type: Char, color: Color, square: Square): Piece? {
    val piece: Piece? = when (type.lowercaseChar()) {
        'p' -> Pawn(color, square)
        'n' -> Knight(color, square)
        'b' -> Bishop(color, square)
        'r' -> Rook(color, square)
        'q' -> Queen(color, square)
        'k' -> King(color, square)
        else -> null
    }
    return piece
}