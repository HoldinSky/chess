package entity.helper

import entity.board.Square

fun squaresToPairs(squareList: ArrayList<Square>): List<Pair<Char, Int>> {
    val list = mutableListOf<Pair<Char, Int>>()

    for (square in squareList) {
        list.add(Pair(square.getFile(), square.getRank()))
    }

    return list
}