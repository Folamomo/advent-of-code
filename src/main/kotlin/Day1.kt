import kotlin.math.abs

class Day1: Day(1) {
    override fun part1(input: List<String>): Int {
        val (list1, list2) = input.toIntPairs().unzip()
        return list1.sorted()
            .zip(list2.sorted())
            .sumOf { (x, y) -> abs(x - y) }
    }

    override fun part2(input: List<String>): Int {
        val (list1, list2) = input.toIntPairs().unzip()
        val count = list1.groupingBy { it }.eachCount()
        return list2.sumOf { it * count.getOrDefault(it, 0) }
    }
}