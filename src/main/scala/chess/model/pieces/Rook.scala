package chess.model.pieces

import chess.model.{Board, Position}

class Rook extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    if (from.row != to.row && from.col != to.col) return false
    if (!isPathClear(from, to, board)) return false
    isCaptureAllowed(to, board, isWhite)
  }

}