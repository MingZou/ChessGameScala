package chess.model.pieces

import chess.model.{Board, Position}

class King extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    val rowDiff = math.abs(from.row - to.row)
    val colDiff = math.abs(from.col - to.col)
    val target = board.get(to)
    rowDiff <= 1 && colDiff <= 1 && (target == '.' || target.isUpper != isWhite)
  }
}
