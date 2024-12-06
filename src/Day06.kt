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

    fun countXInGrid(grid: Array<Array<Pair<Char, Direction>>>): Int {
        var count = 0
        for (row in grid) {
            for (cell in row) {
                if (cell.first == 'X') {
                    count++
                }
            }
        }
        return count
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

    lateinit var completedGrid: Array<Array<Char>>

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
                completedGrid = visitedGrid
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

    fun MutableMap<String, Int>.incrementMapValue(key: String) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }

    fun traverseLabWithDirection(
        grid: Array<Array<Char>>,
        visitedGrid: Array<Array<Pair<Char, Direction>>>,
        startIndex: Pair<Int, Int>,
        startDirection: Direction
    ): Int {
        var currentIndex = startIndex
        var currentDirection = startDirection
        val visitedSet = mutableMapOf<String, Int>()

        while (true) {
            val key = "${currentIndex.first}, ${currentIndex.second}"
            visitedSet.incrementMapValue(key)
            if (visitedSet[key]!! >= 5) {
                break
            }



//            if (visitedGrid[currentIndex.first][currentIndex.second].first == 'X' && visitedGrid[currentIndex.first][currentIndex.second].second == currentDirection) {
//                break
//            }

            visitedGrid[currentIndex.first][currentIndex.second] = Pair('X', currentDirection)

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
                else -> {
                    println("STUCK STUCK STUCK")
                    currentIndex to Direction.NORTH
                }
            }

            currentIndex = nextStep.first
            currentDirection = nextStep.second
        }
        return -1
    }

    fun part1(input: List<String>): Long {

        val grid = readGrid(input)
        println("GridSize: ${grid.size}")

        val startIndex = findItem(grid, '^') ?: throw IllegalStateException("Couldn't find start") // y, x

        println("Start: $startIndex")

        val visited = Array(grid.size) { i -> grid[i].copyOf() }
        val count = traverseLab(grid, visited, startIndex, Direction.NORTH)


        println("Count: $count")
        return count.toLong()
    }


    fun part2(input: List<String>): Long {
        val grid = readGrid(input)
        println("GridSize: ${grid.size}")

        val startIndex = findItem(grid, '^') ?: throw IllegalStateException("Couldn't find start") // y, x
        println("Start: $startIndex")

        val visited = Array(grid.size) { i -> grid[i].copyOf() }
        traverseLab(grid, visited, startIndex, Direction.NORTH)

        printGrid(visited)

        var count = 0
        for (row in grid.indices) {
            for (col in grid.indices) {
                val gridCopy = Array(grid.size) { i -> grid[i].copyOf() }

                if (visited[row][col] == 'X' && gridCopy[row][col] != '#' && gridCopy[row][col] != '^') {
                    gridCopy[row][col] = '#'

                    val visitedWithDirection = Array(gridCopy.size) { i ->
                        Array(gridCopy[i].size) { j ->
                            Pair(
                                gridCopy[i][j],
                                Direction.NONE
                            )
                        }
                    }
                    val found = traverseLabWithDirection(gridCopy, visitedWithDirection, startIndex, Direction.NORTH)

                    if (found == -1) {
                        println("Found $count %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                        printGrid(gridCopy)
                        count++
                    }
                }


            }
        }



        println("Count: $count")
        return count.toLong()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 6L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
//    part1(input).println()
    part2(input).println()
}