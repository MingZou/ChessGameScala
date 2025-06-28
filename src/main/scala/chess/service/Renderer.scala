package chess.service

import chess.model.{Board, Position}

object Renderer {
  def renderBoard(board: Board): Unit = {
    for (row <- 7 to 0 by -1) {
      print((row + 1) + " ")
      for (col <- 0 until 8) {
        val piece = board.get(Position(7-row, col))
        print(piece + " ")
      }
      println()
    }
    println("  a b c d e f g h")
  }
}
