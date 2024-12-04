enum class Direction {
    NONE,
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTHEAST,
    NORTHWEST,
    SOUTHEAST,
    SOUTHWEST
}


fun main() {

    val nextLetterMap = mapOf('X' to 'M', 'M' to 'A', 'A' to 'S')

    fun checkAround(grid: Array<Array<Char>>, y: Int, x: Int, currentLetter: Char, currenDirection: Direction): Int {
        if (currentLetter == 'S') {
            return 1
        } else {
            val rowLength = grid[0].size
            val colLength = grid.size
            var count = 0

            // Top
            if (y > 0 && (currenDirection == Direction.NORTH || currenDirection == Direction.NONE)) {
                val topLetter = grid[y - 1][x]
                if (topLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y - 1, x, topLetter, Direction.NORTH)
                }
            }

            // Bottom
            if (y < colLength - 1 && (currenDirection == Direction.SOUTH || currenDirection == Direction.NONE)) {
                val bottomLetter = grid[y + 1][x]
                if (bottomLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y + 1, x, bottomLetter, Direction.SOUTH)
                }
            }

            // Left
            if (x > 0 && (currenDirection == Direction.WEST || currenDirection == Direction.NONE)) {
                val leftLetter = grid[y][x - 1]
                if (leftLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y, x - 1, leftLetter, Direction.WEST)
                }
            }

            // Right
            if (x < rowLength - 1 && (currenDirection == Direction.EAST || currenDirection == Direction.NONE)) {
                val rightLetter = grid[y][x + 1]
                if (rightLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y, x + 1, rightLetter, Direction.EAST)
                }
            }

            // Top-left
            if (y > 0 && x > 0 && (currenDirection == Direction.NORTHWEST || currenDirection == Direction.NONE)) {
                val topLeftLetter = grid[y - 1][x - 1]
                if (topLeftLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y - 1, x - 1, topLeftLetter, Direction.NORTHWEST)
                }
            }

            // Top-right
            if (y > 0 && x < rowLength - 1 && (currenDirection == Direction.NORTHEAST || currenDirection == Direction.NONE)) {
                val topRightLetter = grid[y - 1][x + 1]
                if (topRightLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y - 1, x + 1, topRightLetter, Direction.NORTHEAST)
                }
            }

            // Bottom-left
            if (y < colLength - 1 && x > 0 && (currenDirection == Direction.SOUTHWEST || currenDirection == Direction.NONE)) {
                val bottomLeftLetter = grid[y + 1][x - 1]
                if (bottomLeftLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y + 1, x - 1, bottomLeftLetter, Direction.SOUTHWEST)
                }
            }

            // Bottom-right
            if (y < colLength - 1 && x < rowLength - 1 && (currenDirection == Direction.SOUTHEAST || currenDirection == Direction.NONE)) {
                val bottomRightLetter = grid[y + 1][x + 1]
                if (bottomRightLetter == nextLetterMap[currentLetter]) {
                    count += checkAround(grid, y + 1, x + 1, bottomRightLetter, Direction.SOUTHEAST)
                }
            }

            return count
        }
    }

    fun part1(input: List<String>): Long {
        val startCoords = mutableListOf<Pair<Int, Int>>()
        val grid = readGrid(input)
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 'X') {
                    startCoords.add(Pair(y, x))
                }
            }
        }

        println("StartCoords $startCoords")
        var count = 0
        for (pair in startCoords) {
            count += checkAround(grid, pair.first, pair.second, 'X', Direction.NONE)
        }

        println("Count: $count")

        return count.toLong()
    }


    fun part2(input: List<String>): Long {
        return 0L
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}