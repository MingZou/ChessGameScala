package chess.model.pieces

import chess.model.{Board, Position}

class Queen extends Piece {
  def isValidMove(from: Position, to: Position, board: Board, isWhite: Boolean): Boolean = {
    val rook = new Rook
    val bishop = new Bishop
    rook.isValidMove(from, to, board, isWhite) || bishop.isValidMove(from, to, board, isWhite)
  }
}