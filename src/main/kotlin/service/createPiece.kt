package service

import entity.helper.Color
import entity.pieces.Knight
import entity.pieces.Pawn
import entity.pieces.Piece

fun createPiece(type: Char, color: Color): Piece? {
    val piece: Piece? = when (type.lowercaseChar()) {
        'p' -> Pawn(color)
        'n' -> Knight(color)

        else -> null
    }

    return piece
}