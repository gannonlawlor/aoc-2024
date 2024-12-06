

fun main() {

    val possiblePlayers = setOf('^', 'v', '<', '>')


    fun findItem(grid: Array<Array<Char>>, item: Char): Pair<Int, Int>? {
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == item) {
                    return Pair(y, x)
                }
            }
        }
        return null
    }

    fun haveVisited(
        visitedGrid: Array<Array<Char>>,
        currentPos: Pair<Int, Int>,
    ): Boolean {
        return visitedGrid[currentPos.first][currentPos.second] == 'X'
    }

    fun printGrid(grid: Array<Array<Char>>) {
        for (row in grid) {
            println(row.joinToString(" "))
        }
    }

    fun countXInGrid(grid: Array<Array<Char>>): Int {
        var count = 0
        for (row in grid) {
            for (cell in row) {
                if (cell == 'X') {
                    count++
                }
            }
        }
        return count
    }

    fun traverseLab(
        grid: Array<Array<Char>>,
        visitedGrid: Array<Array<Char>>,
        startIndex: Pair<Int, Int>,
        startDirection: Direction
    ): Int {
        var currentIndex = startIndex
        var currentDirection = startDirection

        while (true) {
            visitedGrid[currentIndex.first][currentIndex.second] = 'X'

            if ((currentIndex.first == 0 && currentDirection == Direction.NORTH)
                || (currentIndex.first == grid.size - 1 && currentDirection == Direction.SOUTH)
                || (currentIndex.second == 0 && currentDirection == Direction.WEST)
                || (currentIndex.second == grid[0].size - 1 && currentDirection == Direction.EAST)
            ) {
                return countXInGrid(visitedGrid)
            }

            val nextStep = when (currentDirection) {
                Direction.NORTH -> if (grid[currentIndex.first - 1][currentIndex.second] != '#')
                    Pair(currentIndex.first - 1, currentIndex.second) to Direction.NORTH
                else currentIndex to Direction.EAST

                Direction.SOUTH -> if (grid[currentIndex.first + 1][currentIndex.second] != '#')
                    Pair(currentIndex.first + 1, currentIndex.second) to Direction.SOUTH
                else currentIndex to Direction.WEST

                Direction.WEST -> if (grid[currentIndex.first][currentIndex.second - 1] != '#')
                    Pair(currentIndex.first, currentIndex.second - 1) to Direction.WEST
                else currentIndex to Direction.NORTH

                Direction.EAST -> if (grid[currentIndex.first][currentIndex.second + 1] != '#')
                    Pair(currentIndex.first, currentIndex.second + 1) to Direction.EAST
                else currentIndex to Direction.SOUTH
                else -> currentIndex to Direction.NORTH
            }

            currentIndex = nextStep.first
            currentDirection = nextStep.second
        }
    }

    fun part1(input: List<String>): Long {

        val grid = readGrid(input)
        println("GridSize: ${grid.size}")

        val startIndex = findItem(grid, '^') ?: throw IllegalStateException("Couldn't find start") // y, x

        println("Start: $startIndex")


        val visited = Array(grid.size) { i -> grid[i].copyOf() }
        val count = traverseLab(grid, visited, startIndex, Direction.NORTH)

//        printGrid(grid)

//        printGrid(visited)

        println("Count: $count")
        return count.toLong()
    }


    fun part2(input: List<String>): Long {
        return 0L
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}