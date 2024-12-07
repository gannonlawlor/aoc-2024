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


    fun checkNext2(solution: Long, currentNumber: Long, currentIndex: Int, numbers: List<Int>) : Long {

//        println("Current Number: $currentNumber Solution: $solution")

        val doneList = currentIndex == numbers.size - 1

        if (currentNumber == solution && doneList) {
//            println("Found Solution")
            return currentNumber
        }

        if (doneList || currentNumber > solution) {
//            println("End of List")
            return -1
        }

        val nextIndex = currentIndex + 1

        val currentNumber3 = currentNumber + numbers[nextIndex]
        val currentNumber2 = currentNumber * numbers[nextIndex]
        val currentNumber1 = "$currentNumber${numbers[nextIndex]}".toLong()

//        println("Add: $currentNumber3 Multiply: $currentNumber2 Combine: $currentNumber1")

        val sumResult = checkNext2(solution, currentNumber3, nextIndex, numbers)
        val multiplyResult = checkNext2(solution, currentNumber2, nextIndex, numbers)
        val combineResult = checkNext2(solution, currentNumber1, nextIndex, numbers)

//        println("SumResult: $sumResult MultiplyResult: $multiplyResult Combine: $combineResult")

//        if (sumResult == solution && doneList) {
//            return currentNumber
//        } else if (multiplyResult == solution && doneList) {
//            return currentNumber
//        } else if (combineResult == solution) {
//            return currentNumber
//        }
        return maxOf(sumResult, multiplyResult, combineResult)

    }


    fun checkLine2(solution: Long, numbers: List<Int>) : Boolean {

        val n = checkNext2(solution, numbers[0].toLong(), 0, numbers)

        if (n != -1L) {
            return true
        }


        return false
    }


    fun part2(input: List<String>): Long {
        var count = 0L
        for (line in input) {
            val s = line.split(":")
            val solution = s[0].toLong()
            val numbers = s[1].splitByWhitespace().filter { it.isNotEmpty() }.map { it.toInt() }
            val good = checkLine2(solution, numbers)
            if (good) {
                println("Solution: $solution")
                count += solution
            }
        }

        return count
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day07_test")
    check(part2(testInput) == 11387L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day07")
//    part1(input).println()
    part2(input).println()
}