import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>): Long {
        val leftNumbers = mutableListOf<Long>()
        val rightNumbers = mutableListOf<Long>()

        for (line in input) {
            val numbers = line.split("   ")
            val leftNumber = numbers[0].toLong()
            val rightNumber = numbers[1].toLong()

            leftNumbers.add(leftNumber)
            rightNumbers.add(rightNumber)
        }

        leftNumbers.sort()
        rightNumbers.sort()

        var totalDistance : Long = 0
        for (i in leftNumbers.indices) {
            println("${leftNumbers[i]}, ${rightNumbers[i]}")
            val diff = leftNumbers[i] - rightNumbers[i]
            totalDistance += diff.absoluteValue
        }

        println("Total distance: $totalDistance")
        return totalDistance
    }

    fun part2(input: List<String>): Long {

        val leftNumbers = mutableListOf<Long>()
        val rightNumbers = mutableListOf<Long>()

        for (line in input) {
            val numbers = line.split("   ")
            val leftNumber = numbers[0].toLong()
            val rightNumber = numbers[1].toLong()

            leftNumbers.add(leftNumber)
            rightNumbers.add(rightNumber)
        }

        var answer = 0L
        for (left in leftNumbers) {
            var multiplier = 0L
            rightNumbers.forEach { right ->
                if (left == right) {
                    multiplier += 1
                }
            }
            val score = left * multiplier
            println("Score: $score ($left, $multiplier)")
            answer += score
        }
        println("Answer: $answer")

        return answer
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
