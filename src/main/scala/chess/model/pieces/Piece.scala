package chess.model.pieces

import chess.model.{Board, Position}

trait Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean
}

