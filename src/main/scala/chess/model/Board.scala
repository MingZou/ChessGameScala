package chess.model

class Board(private val grid: Array[Array[Char]]) {
  def get(pos: Position): Char = grid(pos.row)(pos.col)


  def move(from: Position, to: Position): Board = {
    val newGrid = grid.map(_.clone())
    newGrid(to.row)(to.col) = newGrid(from.row)(from.col)
    newGrid(from.row)(from.col) = '.'
    new Board(newGrid)
  }

}



object Board {
  def initial(): Board = {
    val grid = Array.fill(8, 8)('.')
    grid(0) = Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r')
    for (col <- 0 until 8) grid(1)(col) = 'p'
    grid(7) = Array('R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R')
    for (col <- 0 until 8) grid(6)(col) = 'P'
    new Board(grid)
  }

  def fromState(rows: Array[String]): Board = {
    val grid = rows.map(_.toCharArray)
    new Board(grid)
  }
}