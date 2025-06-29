package chess

import org.scalatest.funsuite.AnyFunSuite
import chess.model.{Board, Position}
import chess.model.pieces._
import chess.service.MoveValidator

class ChessGameSpec extends AnyFunSuite {

  test("initial board has correct pieces") {
    val board = Board.initial()
    assert(board.get(Position(0, 0)) == 'r') // black rook
    assert(board.get(Position(7, 4)) == 'K') // white king
  }

  test("pawn can move forward one square") {
    val board = Board.initial()
    val pawn = new Pawn
    assert(pawn.isValidMove(Position(6, 0), Position(5, 0), board, isWhite = true))
  }

  test("pawn can move forward two squares from initial position") {
    val board = Board.initial()
    val pawn = new Pawn
    assert(pawn.isValidMove(Position(6, 0), Position(4, 0), board, isWhite = true))
  }

  test("rook cannot move diagonally") {
    val board = Board.initial()
    val rook = new Rook
    assert(!rook.isValidMove(Position(7, 0), Position(6, 1), board, isWhite = true))
  }

  test("bishop can move diagonally when path is clear") {
    val board = Board.fromState(
      Array(
        "........",
        "........",
        "........",
        "........",
        "........",
        "...B....",
        "........",
        "........"
      )
    )
    val bishop = new Bishop
    assert(bishop.isValidMove(Position(5, 3), Position(2, 0), board, isWhite = true))
  }

  test("knight can jump over pieces") {
    val board = Board.initial()
    val knight = new Knight
    assert(knight.isValidMove(Position(7, 1), Position(5, 2), board, isWhite = true))
  }

  test("invalid move: no piece at source") {
    val board = Board.initial()
    val result = MoveValidator.isValidMove('.', Position(4, 4), Position(4, 5), board, isWhiteTurn = true)
    assert(!result)
  }

  test("invalid move: moving opponent's piece") {
    val board = Board.initial()
    val result = MoveValidator.isValidMove('r', Position(0, 0), Position(1, 0), board, isWhiteTurn = true)
    assert(!result)
  }

  test("queen moves like rook or bishop") {
    val board = Board.fromState(
      Array(
        "........",
        "........",
        "........",
        "....Q...",
        "........",
        "........",
        "........",
        "........"
      )
    )
    val queen = new Queen
    assert(queen.isValidMove(Position(3, 4), Position(3, 7), board, isWhite = true)) // horizontal
    assert(queen.isValidMove(Position(3, 4), Position(0, 1), board, isWhite = true)) // diagonal
  }

  test("bishop cannot move when path is blocked") {
    val board = Board.fromState(
        Array(
            "........",
            "........",
            "........",
            "........",
            "..p.....",  // (4, 2)
            "...B....",  // (5, 3)
            "........",
            "........"
        )
    )

    val bishop = new Bishop
    assert(!bishop.isValidMove(Position(5, 3), Position(2, 0), board, isWhite = true))
  }

  test("rook cannot move through a piece") {
    val board = Board.fromState(
      Array(
        "........",
        "........",
        "........",
        "...p....",
        "........",
        "...R....",
        "........",
        "........"
      )
    )
    val rook = new Rook
    assert(!rook.isValidMove(Position(5, 3), Position(2, 3), board, isWhite = true))
  }

  test("king cannot move more than one square") {
    val board = Board.fromState(
      Array(
        "........",
        "........",
        "........",
        "........",
        "........",
        "........",
        "........",
        "....K..."
      )
    )
    val king = new King
    assert(!king.isValidMove(Position(7, 4), Position(5, 4), board, isWhite = true))
  }

  test("knight cannot move in a straight line") {
    val board = Board.fromState(
      Array(
        "........",
        "........",
        "........",
        "........",
        "........",
        "........",
        "........",
        "..N....."
      )
    )
    val knight = new Knight
    assert(!knight.isValidMove(Position(7, 2), Position(5, 2), board, isWhite = true))
  }

}
