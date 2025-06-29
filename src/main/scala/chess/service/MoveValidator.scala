package chess.service

import chess.model.{Board, Position}
import chess.model.pieces._

object MoveValidator {
  def isValidMove(pieceChar: Char, from: Position, to: Position, board: Board, isWhiteTurn: Boolean): Boolean = {
    if (pieceChar == '.')  {
      println(s"Invalid move: no piece at ${from}")
      return false
    }
    val isWhitePiece = pieceChar.isUpper
    if (isWhitePiece != isWhiteTurn) {
      println(s"Invalid move: it's ${if (isWhiteTurn) "White" else "Black"}'s turn, but tried to move ${if (isWhitePiece) "White" else "Black"} piece $pieceChar")
      return false
    }

    val piece: Piece = pieceChar.toLower match {
      case 'p' => new Pawn
      case 'r' => new Rook
      case 'n' => new Knight
      case 'b' => new Bishop
      case 'q' => new Queen
      case 'k' => new King
      case _   => return false
    }

    piece.isValidMove(from, to, board, isWhitePiece)
  }
}
