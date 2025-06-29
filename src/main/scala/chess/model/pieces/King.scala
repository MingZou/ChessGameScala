package chess.model.pieces

import chess.model.{Board, Position}

class King extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    val rowDiff = math.abs(from.row - to.row)
    val colDiff = math.abs(from.col - to.col)
    rowDiff <= 1 && colDiff <= 1 && isCaptureAllowed(to, board, isWhite)
  }
}
