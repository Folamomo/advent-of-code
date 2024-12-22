import java.nio.file.Path

class Day11: Day(11) {
    override fun part1(input: Path): Long {
        return input.toFile()
            .readText()
            .split(" ")
            .map(String::toLong)
            .sumOf { calculateHashed(Pair(it, 25)) }
    }

    override fun part2(input: Path): Long {
        return input.toFile()
            .readText()
            .split(" ")
            .map(String::toLong)
            .sumOf { calculateHashed(Pair(it, 75)) }
    }

    private val cache = HashMap<Pair<Long, Int>, Long>()

    private fun calculateHashed(pair: Pair<Long, Int>): Long {
        val found = cache[pair]
        if (found != null) return found
        val calculated = calculate(pair)
        cache[pair] = calculated
        return calculated
    }

    private fun calculate(pair: Pair<Long, Int>): Long {
        val (stone, depth) = pair
        if (depth == 0) return 1
        if (stone == 0L) return calculateHashed(Pair(1, depth - 1))
        val s = stone.toString()
        if (s.length % 2 == 0) {
            return calculateHashed(Pair(s.substring(0, s.length/2).toLong(), depth - 1)) +
                    calculateHashed(Pair(s.substring(s.length/2).toLong(), depth - 1))
        }
        return calculateHashed(Pair(stone * 2024, depth - 1))
    }
}