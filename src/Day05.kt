fun main() {

    fun getOrder(input: List<String>): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()

        for (line in input) {
            if (line.contains("|")) {
                val rule = line.split("|")
                list.add(Pair(rule[0].toInt(), rule[1].toInt()))
            }
        }
        println("Rules: $list")
        return list.toList()
    }

    fun getPageNumbers(input: List<String>): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        for (line in input) {
            if (line.contains(",")) {
                val order = line.split(",")
                list.add(order.map { it.toInt() }.toList())
            }
        }
        println("Page Numbers: $list")
        return list
    }

    fun sumMiddleElements(lists: List<List<Int>>): Int {
        return lists.sumOf { sublist ->
            if (sublist.isEmpty()) 0
            else sublist[sublist.size / 2]
        }
    }

    fun part1(input: List<String>): Long {
        val pageNumbers = getPageNumbers(input)
        val order = getOrder(input)
        var validPages = mutableListOf<List<Int>>()

        for (page in pageNumbers) {
            // Increment through every number on the page
            var validPage = true
            for ((index, pageNumber) in page.withIndex()) {
                // At the End

                val subList = page.subList(index + 1, page.size)
                var pagesLeftTocheck = subList.size
                println("Pages Left: $pagesLeftTocheck")
                println("Sublist: $subList")
                for (o in order) {
                    if (o.first == pageNumber && subList.contains(o.second)) {
                        pagesLeftTocheck--
                    }
                }

                validPage = pagesLeftTocheck == 0
                println("Page: $page Valid: $validPage")

                if (!validPage) {
                    break
                }

            }

            if (validPage) {
                validPages.add(page)
            }
        }

        println("Valid Pages: $validPages")

        return sumMiddleElements(validPages).toLong()
    }

    fun MutableMap<Int, Int>.incrementMapValue(key: Int) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }

    fun <K> findKeyForValue(map: Map<K, Int>, number: Int): K? {
        return map.entries.find { it.value == number }?.key
    }


    fun part2(input: List<String>): Long {
        val pageNumbers = getPageNumbers(input)
        val order = getOrder(input)
        var inValidPages = mutableListOf<List<Int>>()

        for (page in pageNumbers) {
            // Increment through every number on the page
            var validPage = true
            for ((index, pageNumber) in page.withIndex()) {
                // At the End

                val subList = page.subList(index + 1, page.size)
                var pagesLeftTocheck = subList.size
//                println("Pages Left: $pagesLeftTocheck")
//                println("Sublist: $subList")
                for (o in order) {
                    if (o.first == pageNumber && subList.contains(o.second)) {
                        pagesLeftTocheck--
                    }
                }

                validPage = pagesLeftTocheck == 0
//                println("Page: $page Valid: $validPage")

                if (!validPage) {
                    break
                }

            }

            if (!validPage) {
                inValidPages.add(page)
            }
        }

        val validPages = mutableListOf<List<Int>>()

        for (page in inValidPages) {

            var validPage = mutableListOf<Int>()
            var map = mutableMapOf<Int, Int>()

            for ((index, pageNumber) in page.withIndex()) {
                // At the End
                val pagesToCheck = page.size - 1 - index
                println("Pages To Check: $pagesToCheck")

                val listWithOut = page.toMutableList()
                listWithOut.remove(pageNumber)

                println("List $listWithOut")

                for (item in listWithOut) {
                    for (o in order) {
                        if (o.first == pageNumber && o.second == item) {
                            map.incrementMapValue(o.first)
                        }
                    }
                }


                // Get element that matches pagesToCheck
//                val n = findKeyForValue(map, pagesToCheck)!!
//                validPage.add(n)
            }
            println("Map: $map")

            val sorted = map.entries
                .sortedBy { it.value }
                .map { it.key }
                .reversed()
                .toMutableList()

            val missingValues = page.filter { it !in sorted }
            sorted.addAll(missingValues)
            validPage = sorted


            validPages.add(validPage)

        }

        return sumMiddleElements(validPages).toLong()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part2(testInput) == 123L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
//    part1(input).println()
    part2(input).println()
}