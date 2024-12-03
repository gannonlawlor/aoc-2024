fun main() {

    fun part1(input: List<String>): Long {
        return 0L
    }


    fun part2(input: List<String>): Long {
        return 0L
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day0_test")
    check(part2(testInput) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day0")
    part1(input).println()
    part2(input).println()
}