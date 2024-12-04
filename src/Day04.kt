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
    val masLetterMap = mapOf('M' to 'A', 'A' to 'S')

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

    fun findMas(grid: Array<Array<Char>>, y: Int, x: Int, currentLetter: Char, currenDirection: Direction): List<Direction> {
        val directions = mutableListOf<Direction>()
        if (currentLetter == 'S') {
            directions.add(currenDirection)
        } else {
            val rowLength = grid[0].size
            val colLength = grid.size

            // Top-left
            if (y > 0 && x > 0 && (currenDirection == Direction.NORTHWEST || currenDirection == Direction.NONE)) {
                val topLeftLetter = grid[y - 1][x - 1]
                if (topLeftLetter == nextLetterMap[currentLetter]) {
                    directions.addAll(findMas(grid, y - 1, x - 1, topLeftLetter, Direction.NORTHWEST))
                }
            }

            // Top-right
            if (y > 0 && x < rowLength - 1 && (currenDirection == Direction.NORTHEAST || currenDirection == Direction.NONE)) {
                val topRightLetter = grid[y - 1][x + 1]
                if (topRightLetter == nextLetterMap[currentLetter]) {
                    directions.addAll(findMas(grid, y - 1, x + 1, topRightLetter, Direction.NORTHEAST))
                }
            }

            // Bottom-left
            if (y < colLength - 1 && x > 0 && (currenDirection == Direction.SOUTHWEST || currenDirection == Direction.NONE)) {
                val bottomLeftLetter = grid[y + 1][x - 1]
                if (bottomLeftLetter == nextLetterMap[currentLetter]) {
                    directions.addAll(findMas(grid, y + 1, x - 1, bottomLeftLetter, Direction.SOUTHWEST))
                }
            }

            // Bottom-right
            if (y < colLength - 1 && x < rowLength - 1 && (currenDirection == Direction.SOUTHEAST || currenDirection == Direction.NONE)) {
                val bottomRightLetter = grid[y + 1][x + 1]
                if (bottomRightLetter == nextLetterMap[currentLetter]) {
                    directions.addAll(findMas(grid, y + 1, x + 1, bottomRightLetter, Direction.SOUTHEAST))
                }
            }
        }
        return directions
    }

    fun MutableMap<String, Int>.incrementMapValue(key: String) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }


    fun part2(input: List<String>): Long {

        val startCoords = mutableListOf<Pair<Int, Int>>()
        val grid = readGrid(input)
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 'M') {
                    startCoords.add(Pair(y, x))
                }
            }
        }

        // Get mas's and return start index
        var count = 0
        var startX = -1
        var startY = -1
        val validMasStart = mutableListOf<Pair<Int, Int>>()
        val aMap = mutableMapOf<String, Int>()
        for (pair in startCoords) {
            val mass = findMas(grid, pair.first, pair.second, 'M', Direction.NONE)
            for (findMas in mass) {
                if (findMas != Direction.NONE) {
                    println("Y: ${pair.first} X: ${pair.second} FindMas: $findMas")
                }

                if (findMas == Direction.SOUTHWEST) {
                    val aY = pair.first + 1
                    val aX = pair.second - 1
                    aMap.incrementMapValue("$aY,$aX")
                } else if (findMas == Direction.SOUTHEAST) {
                    val aY = pair.first + 1
                    val aX = pair.second + 1
                    aMap.incrementMapValue("$aY,$aX")
                } else if (findMas == Direction.NORTHEAST) {
                    val aY = pair.first - 1
                    val aX = pair.second + 1
                    aMap.incrementMapValue("$aY,$aX")
                } else if (findMas == Direction.NORTHWEST) {
                    val aY = pair.first - 1
                    val aX = pair.second - 1
                    aMap.incrementMapValue("$aY,$aX")
                }
            }
        }

        println("AMap $aMap")

        count = aMap.values.count { it == 2 }


        return count.toLong()
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 9L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
//    part1(input).println()
    println("Part 2")
    part2(input).println()
}