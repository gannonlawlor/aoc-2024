fun main() {

    fun checkNext(solution: Long, currentNumber: Long, currentIndex: Int, numbers: List<Int>) : Long {

        println("Current Number: $currentNumber")

        if (currentNumber == solution) {
            return currentNumber
        }

        if (currentIndex == numbers.size - 1) {
            return -1
        }

        val nextIndex = currentIndex + 1

        // Calculate both possibilities and choose based on your criteria
        val sumResult = checkNext(solution,currentNumber + numbers[nextIndex], nextIndex, numbers)
        val multiplyResult = checkNext(solution,currentNumber * numbers[nextIndex], nextIndex, numbers)

        // Return the result you want (e.g., maximum of both operations)
        return maxOf(sumResult, multiplyResult)

    }

    fun checkLine(solution: Long, numbers: List<Int>) : Boolean {

        val n = checkNext(solution, numbers[0].toLong(), 0, numbers)

        if (n != -1L) {
            return true
        }


        return false
    }

    fun part1(input: List<String>): Long {

        var count = 0L
        for (line in input) {
            val s = line.split(":")
            val solution = s[0].toLong()
            val numbers = s[1].splitByWhitespace().filter { it.isNotEmpty() }.map { it.toInt() }
            val good = checkLine(solution, numbers)
            if (good) {
                println("Solution: $solution")
                count += solution
            }
        }

        return count
    }




    fun part2(input: List<String>): Long {
        return 0L
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day07_test")
    check(part2(testInput) == 3749L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}