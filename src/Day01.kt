import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (list1, list2) = input.toIntPairs().unzip()
        return list1.sorted()
            .zip(list2.sorted())
            .sumOf { (x, y) -> abs(x - y) }
    }

    fun part2(input: List<String>): Int {
        val (list1, list2) = input.toIntPairs().unzip()
        val count1 = list1.groupingBy { it }.eachCount()
        val count2 = list2.groupingBy { it }.eachCount()
        return count1.asSequence().sumOf { (k, v) -> k * v * count2.getOrDefault(k, 0) }
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 11)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
