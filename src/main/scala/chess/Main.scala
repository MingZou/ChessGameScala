package chess
import com.whitehatgaming.UserInputFile
import chess.model.{Board, Position}
import chess.model.pieces.Piece
import chess.service.{Renderer, MoveValidator}
import java.util.NoSuchElementException
import scala.util.control.Breaks._

object Main extends App {
 if (args.length != 1) {
    println("Usage: run <path-to-move-file>")
    sys.exit(1)
  }
  val input = new UserInputFile(args(0))
  var board = Board.initial()
  var currentPlayerIsWhite = true

  Renderer.renderBoard(board)

  var hasNext = true
  while (hasNext) {
      val move = input.nextMove() 
      if (move != null) { 
        val fromCol = move(0)
        val fromRow = move(1)
        val toCol   = move(2)
        val toRow   = move(3)

        val from = Position(fromRow, fromCol)
        val to   = Position(toRow, toCol)

        val piece = board.get(from)

        
        if (MoveValidator.isValidMove(piece, from, to, board, currentPlayerIsWhite)) {
          board = board.move(from, to)
          println(s"Move: ${posToNotation(from)} -> ${posToNotation(to)}")
          Renderer.renderBoard(board)
          currentPlayerIsWhite = !currentPlayerIsWhite
        } else {
          println(s"Invalid move for piece $piece: ${posToNotation(from)} -> ${posToNotation(to)}")
          println("Game stopped due to invalid move.")
          hasNext = false
        }
        Thread.sleep(1000)
        
      }
      else {
        hasNext = false
        println("-------No more moves.------")
      }
    }


  def posToNotation(pos: Position): String = {
    val file = ('a' + pos.col).toChar
    val rank = (8 - pos.row).toString
    s"$file$rank"
  }
}
