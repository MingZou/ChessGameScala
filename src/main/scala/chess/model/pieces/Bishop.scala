package chess.model.pieces

import chess.model.{Board, Position}

class Bishop extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    if (math.abs(from.row - to.row) != math.abs(from.col - to.col)) return false
    if (!isPathClear(from, to, board)) return false
    isCaptureAllowed(to, board, isWhite)
  }

  
}