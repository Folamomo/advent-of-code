import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.abs

class Day2 : Day(2) {
    override fun part1(input: Path): Int {
        return input.readLines().count { isSafe(it.split(whitespaceRegex).map(String::toInt)) }
    }

    private fun isSafe(levels: List<Int>, startAt: Int = 0): Boolean {
        val direction = levels[0].compareTo(levels[1])
        if (direction == 0) return false
        for (i in startAt until levels.size - 1) {
            if (levels[i].compareTo(levels[i + 1]) != direction || !checkDistance(levels[i], levels[i + 1])) {
                return false
            }
        }
        return true
    }

    private fun checkDistance(x: Int, y: Int): Boolean {
        return abs(x - y) <= 3
    }

    override fun part2(input: Path): Int {
        return input.readLines().count { isSafeWithDampening(it.split(whitespaceRegex).map(String::toInt)) }
    }

    private fun isSafeWithDampening(levels: List<Int>): Boolean {
        val direction = levels[0].compareTo(levels[1])

        if (!checkDistance(levels[0], levels[1]) || direction == 0) {
            return isSafe(levels.without(0)) || isSafe(levels.without(1))
        }

        if (direction != levels[1].compareTo(levels[2])) {
            return isSafe(levels.without(0)) || isSafe(levels.without(1)) || isSafe(levels.without(2))
        }

        for (i: Int in 1 until levels.size - 1) {
            if (levels[i].compareTo(levels[i + 1]) != direction || !checkDistance(levels[i], levels[i + 1])) {
                return isSafe(levels.without(i), i - 1) || isSafe(levels.without(i + 1), i - 1)
            }
        }

        return true
    }
}
