package chess.model.pieces

import chess.model.{Board, Position}

class Pawn extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    val dir = if (isWhite) -1 else 1
    val startRow = if (isWhite) 6 else 1
    val oneStep = from.row + dir == to.row && from.col == to.col && board.get(to) == '.'
    val twoStep = from.row == startRow && to.row == from.row + 2 * dir && from.col == to.col &&
                  board.get(Position(from.row + dir, from.col)) == '.' && board.get(to) == '.'
    val capture = from.row + dir == to.row && math.abs(from.col - to.col) == 1 && board.get(to) != '.' &&
                  board.get(to).isUpper != isWhite

    oneStep || twoStep || capture
  }
}