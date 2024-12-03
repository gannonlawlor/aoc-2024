import kotlin.math.absoluteValue

fun main() {

    val allowedDifference = setOf(1, 2, 3)

    fun part1(input: List<String>): Long {
        var count = 0
        for (line in input) {
            val numbers = line.splitByWhitespace().map { it.toInt() }

            val increasing = numbers[0] < numbers[1]
            var isSafe = false
            for (i in numbers.indices) {
                // end
                if (i != numbers.size - 1) {
                    val first = numbers[i]
                    val second = numbers[i + 1]
                    val difference = second - first

                    println("First $first Second $second difference $difference increasing $increasing")
                    if (difference > 0 && allowedDifference.contains(difference) && increasing) {
                        isSafe = true
                    } else if (difference < 0 && allowedDifference.contains(difference.absoluteValue) && !increasing) {
                        isSafe = true
                    } else {
                        isSafe = false
                        break
                    }
                }
            }

            println("Line: $line isSafe: $isSafe increasing: $increasing")

            if (isSafe) {
                count++
            }
        }

        return count.toLong()
    }


    fun part2(input: List<String>): Long {

        var count = 0
        for (line in input) {
            val numbers = line.splitByWhitespace().map { it.toInt() }

            var increasing = numbers[0] < numbers[1]
            var isSafe = false
            var failedIndex = -1
            for (i in numbers.indices) {
                // end
                if (i != numbers.size - 1) {
                    val first = numbers[i]
                    val second = numbers[i + 1]
                    val difference = second - first

//                    println("First $first Second $second difference $difference increasing $increasing")
                    if (difference > 0 && allowedDifference.contains(difference) && increasing) {
                        isSafe = true
                    } else if (difference < 0 && allowedDifference.contains(difference.absoluteValue) && !increasing) {
                        isSafe = true
                    } else {
                        isSafe = false
                        failedIndex = i
                        break
                    }
                }
            }

            if (!isSafe) {

                // Check with failedIndex removed
                val numbers2 = numbers.toMutableList()
                numbers2.removeAt(failedIndex)
                println("Numbers2 $numbers2 FailedIndex: $failedIndex")

                increasing = numbers2[0] < numbers2[1]
                isSafe = false
                for (i in numbers2.indices) {
                    // end
                    if (i != numbers2.size - 1) {
                        val first = numbers2[i]
                        val second = numbers2[i + 1]
                        val difference = second - first

//                        println("First $first Second $second difference $difference increasing $increasing")
                        if (difference > 0 && allowedDifference.contains(difference) && increasing) {
                            isSafe = true
                        } else if (difference < 0 && allowedDifference.contains(difference.absoluteValue) && !increasing) {
                            isSafe = true
                        } else {
                            isSafe = false
                            break
                        }
                    }
                }


                // Check with failedIndex + 1 removed
                if (!isSafe) {
                    val numbers3 = numbers.toMutableList()
                    numbers3.removeAt(failedIndex + 1)

                    println("Numbers3 $numbers3 FailedIndex + 1: ${failedIndex + 1}")


                    increasing = numbers3[0] < numbers3[1]
                    isSafe = false
                    for (i in numbers3.indices) {
                        // end
                        if (i != numbers3.size - 1) {
                            val first = numbers3[i]
                            val second = numbers3[i + 1]
                            val difference = second - first

//                            println("First $first Second $second difference $difference increasing $increasing")
                            if (difference > 0 && allowedDifference.contains(difference) && increasing) {
                                isSafe = true
                            } else if (difference < 0 && allowedDifference.contains(difference.absoluteValue) && !increasing) {
                                isSafe = true
                            } else {
                                isSafe = false
                                break
                            }
                        }
                    }


                    if (!isSafe && failedIndex > 0) {
                        // Check with failedIndex removed
                        val numbers4 = numbers.toMutableList()
                        numbers4.removeAt(failedIndex - 1)
                        println("numbers4 $numbers4 FailedIndex - 1: ${failedIndex - 1}")

                        increasing = numbers4[0] < numbers4[1]
                        isSafe = false
                        for (i in numbers4.indices) {
                            // end
                            if (i != numbers4.size - 1) {
                                val first = numbers4[i]
                                val second = numbers4[i + 1]
                                val difference = second - first

//                        println("First $first Second $second difference $difference increasing $increasing")
                                if (difference > 0 && allowedDifference.contains(difference) && increasing) {
                                    isSafe = true
                                } else if (difference < 0 && allowedDifference.contains(difference.absoluteValue) && !increasing) {
                                    isSafe = true
                                } else {
                                    isSafe = false
                                    break
                                }
                            }
                        }
                    }
                }

                println("Line: $line isSafe: $isSafe")

            }


            if (isSafe) {
                count++
            }
        }

        println("Count $count")

        return count.toLong()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 10L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}