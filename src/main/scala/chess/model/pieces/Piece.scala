package chess.model.pieces

import chess.model.{Board, Position}

trait Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean

  protected def isCaptureAllowed(to: Position, board: Board, isWhite: Boolean): Boolean = {
    val target = board.get(to)
    if (target != '.' && target.isUpper == isWhite) {
      println("Invalid move: cannot capture your own piece.")
      return false
    }
    true
  }
}

