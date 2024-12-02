import kotlin.math.abs

class Day2: Day(2) {
    override fun part1(input: List<String>): Int {
        return input.count { isSafe(it.split(whitespaceRegex).map(String::toInt)) }
    }

    private fun isSafe(levels: List<Int>): Boolean {
        return when{
            levels[0] < levels[1] -> isSafeAscending(levels)
            levels[0] > levels[1] -> isSafeDescending(levels)
            else ->  false
        }
    }

    fun checkDistance(x: Int, y: Int): Boolean {
        return abs(x - y) <= 3
    }

    fun isSafeAscending(levels: List<Int>): Boolean{
        for (i: Int in 0 until levels.size-1) {
            if (!checkDistance(levels[i], levels[i + 1]) || !(levels[i] < levels[i + 1])) {
                return false
            }
        }
        return true
    }

    fun isSafeDescending(levels: List<Int>): Boolean{
        for (i: Int in 0 until levels.size-1) {
            if (!checkDistance(levels[i], levels[i + 1]) || !(levels[i] > levels[i + 1])) {
                return false
            }
        }
        return true
    }

    override fun part2(input: List<String>): Int {
        return input.count { isSafeWithDampening(it.split(whitespaceRegex).map(String::toInt)) }
    }

    private fun isSafeWithDampening(levels: List<Int>): Boolean {
        return  (
                    levels[0] < levels[1] &&
                    checkDistance(levels[0], levels[1]) &&
                    isSafeAscendingWithDampening(levels)
                ) ||
                (
                    levels[0] > levels[1] &&
                    checkDistance(levels[0], levels[1]) &&
                    isSafeDescendingWithDampening(levels)
                ) ||
                isSafe(levels.subList(1, levels.size)) ||
                isSafe(levels.without(1))
    }

    private fun isSafeAscendingWithDampening(levels: List<Int>): Boolean{
        return levels.size <= 3 ||
                (checkDistance(levels[1], levels[2]) &&
                    levels[1] < levels[2] &&
                    isSafeAscendingWithDampening(levels.subList(1, levels.size))
                ) ||
                isSafeAscending(levels.without(1)) ||
                isSafeAscending(levels.without(2))

    }

    private fun isSafeDescendingWithDampening(levels: List<Int> ): Boolean{
        return levels.size <= 3 ||
                (checkDistance(levels[1], levels[2]) &&
                        levels[1] > levels[2] &&
                        isSafeDescendingWithDampening(levels.subList(1, levels.size))
                        ) ||
                isSafeDescending(levels.without(1)) ||
                isSafeDescending(levels.without(2))
    }
}