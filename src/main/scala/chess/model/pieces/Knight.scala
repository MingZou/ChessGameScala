package chess.model.pieces

import chess.model.{Board, Position}

class Knight extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    val rowDiff = math.abs(from.row - to.row)
    val colDiff = math.abs(from.col - to.col)
    val target = board.get(to)
    (rowDiff, colDiff) match {
      case (2, 1) | (1, 2) => target == '.' || target.isUpper != isWhite
      case _ => false
    }
  }
}
