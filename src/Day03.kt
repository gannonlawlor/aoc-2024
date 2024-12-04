fun main() {

    fun part1(input: List<String>): Long {
        var ans = 0
        for (line in input) {
            val regex = """mul\(\d+,\d+\)""".toRegex()
            val muls = regex.findAll(line).map { it.value }.toList()
            println("Muls: $muls")
            for (mul in muls) {
                val sp = mul.split(",")

                val first = sp[0].substringAfter("mul(").toInt()
                val second = sp[1].substringBefore(")").toInt()
                val mul = first * second
                ans += mul
            }
        }
        return ans.toLong()
    }

    fun findDoDontMatchesWithIndex(input: String): Map<Int, String> {
        val regex = """don't\(\)|do\(\)""".toRegex()
        return regex.findAll(input).associate { it.range.first to it.value }
    }


    fun part2(input: List<String>): Long {
        val allDs = mutableMapOf<Int, String>()
        val allMul = mutableMapOf<Int, String>()
        var allLines = ""
        for (line in input) {
            allLines += line
        }

        val ds = findDoDontMatchesWithIndex(allLines)
        allDs.putAll(ds)
        val regex = """mul\(\d+,\d+\)""".toRegex()
        val muls = regex.findAll(allLines).associate { it.range.first to it.value }
        allMul.putAll(muls)


        val max = allMul.maxBy { it.key }.key
        println("Max: $max")
        var ans = 0
        var counts = true

        for (i in 0..max) {

            if (allDs.contains(i)) {
                val isDont = allDs[i].equals("don't()")
                println("isDont $isDont allDs ${allDs[i]}")
                counts = !isDont
            }

            if (counts && allMul.contains(i)) {
                val li = allMul[i]
                println("allMul ${allMul[i]}")

                val sp = li!!.split(",")

                val first = sp[0].substringAfter("mul(").toInt()
                val second = sp[1].substringBefore(")").toInt()
                val mul = first * second
                ans += mul
            }
        }

        return ans.toLong()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 161L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
//    part1(input).println()
    part2(input).println()
}