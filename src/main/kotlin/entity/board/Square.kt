package entity.board

import entity.pieces.Piece

class Square(
    private val board: ChessBoard,
    private var file: Char,
    private var rank: Int
) {
    private var piece: Piece? = null

    constructor(board: ChessBoard, file: Char, rank: Int, piece: Piece) : this(board, file, rank) {
        this.piece = piece
    }

    fun getBoard(): ChessBoard {
        return this.board
    }

    fun getFile(): Char {
        return this.file
    }

    fun getRank(): Int {
        return this.rank
    }

    fun getPiece(): Piece? {
        return this.piece
    }

    fun setPiece(value: Piece) {
        this.piece = value
    }

    fun removePiece() {
        this.piece = null
    }

    fun isBlank(): Boolean {
        return this.piece == null
    }

    override fun toString(): String {
        if (piece == null) return " "
        return piece.toString()
    }
}
