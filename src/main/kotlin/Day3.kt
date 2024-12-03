import java.nio.file.Path
import kotlin.io.path.readText

class Day3: Day(3) {
    private val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()
    private fun calculateMatch(it: MatchResult): Int = it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt()

    override fun part1(input: Path): Int {
        return mulRegex.findAll(input.readText())
            .sumOf(::calculateMatch)
    }

    override fun part2(input: Path): Int {
        val regex = """do\(\)|don't\(\)|mul\((\d+),(\d+)\)""".toRegex()
        var result = 0
        var enabled = true

        for (match in regex.findAll(input.readText())) {
            when {
                match.value == "do()" -> enabled = true
                match.value == "don't()" -> enabled = false
                enabled -> result += calculateMatch(match)
            }
        }

        return result
    }
}