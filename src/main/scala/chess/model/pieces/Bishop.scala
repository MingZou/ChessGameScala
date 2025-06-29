package chess.model.pieces

import chess.model.{Board, Position}

class Bishop extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    if (math.abs(from.row - to.row) != math.abs(from.col - to.col)) return false
    if (!isPathClear(from, to, board)) return false
    isCaptureAllowed(to, board, isWhite)
  }

  private def isPathClear(from: Position, to: Position, board: Board): Boolean = {
    val rowStep = Integer.compare(to.row, from.row)
    val colStep = Integer.compare(to.col, from.col)
    var cur = Position(from.row + rowStep, from.col + colStep)
    while (cur != to) {
      if (board.get(cur) != '.') return false
      cur = Position(cur.row + rowStep, cur.col + colStep)
    }
    true
  }
}