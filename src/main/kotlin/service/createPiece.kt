package service

import entity.Square
import entity.helper.Color
import entity.pieces.Bishop
import entity.pieces.Knight
import entity.pieces.Pawn
import entity.pieces.Piece

fun createPiece(type: Char, color: Color): Piece? {
    val piece: Piece? = when (type.lowercaseChar()) {
        'p' -> Pawn(color)
        'n' -> Knight(color)
        'b' -> Bishop(color)
        else -> null
    }

    return piece
}

fun createPiece(type: Char, color: Color, square: Square): Piece? {
    val piece = createPiece(type, color)
    piece?.setPosition(square)
    return piece
}