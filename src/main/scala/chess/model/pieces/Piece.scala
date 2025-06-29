package chess.model.pieces

import chess.model.{Board, Position}

trait Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean

  protected def isPathClear(from: Position, to: Position, board: Board): Boolean = {
    val rowStep = Integer.compare(to.row, from.row)
    val colStep = Integer.compare(to.col, from.col)
    var cur = Position(from.row + rowStep, from.col + colStep)
    while (cur != to) {
      if (board.get(cur) != '.')  {
      println("Invalid move: path not clear")
      return false
    }
      cur = Position(cur.row + rowStep, cur.col + colStep)
    }
    true
  }

  protected def isCaptureAllowed(to: Position, board: Board, isWhite: Boolean): Boolean = {
    val target = board.get(to)
    if (target != '.' && target.isUpper == isWhite) {
      println("Invalid move: cannot capture your own piece.")
      return false
    }
    true
  }

}

