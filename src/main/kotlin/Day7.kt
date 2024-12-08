import java.nio.file.Path
import kotlin.io.path.readLines

class Day7: Day(7) {
    override fun part1(input: Path): Long {
        val equations = input.readLines().map {
            val s = it.split(':', ' ')
            Pair(s[0].toLong(), s.drop(2).map (String::toLong))
        }

        return equations.filter(::check).sumOf {it.first}
    }


    private fun check(equation: Pair<Long, List<Long>>): Boolean{
        val (result, arguments) = equation
        val last = arguments.last()
        if (arguments.size == 1) return result == last
        if (result % last == 0L && check(Pair(result/last, arguments.subList(0, arguments.size - 1)))) return true
        return result - last > 0 && check(Pair(result - last, arguments.subList(0, arguments.size - 1)))
    }

    override fun part2(input: Path): Long {
        val equations = input.readLines().map {
            val s = it.split(':', ' ')
            Pair(s[0].toLong(), s.drop(2).map (String::toLong))
        }

        return equations.filter(::check3).sumOf {it.first}
    }

    private fun check3(equation: Pair<Long, List<Long>>): Boolean{
        val (result, arguments) = equation
        val last = arguments.last()
        if (arguments.size == 1) return result == last
        if (result % last == 0L && check3(Pair(result/last, arguments.subList(0, arguments.size - 1)))) return true
        if (checkConcat(equation)) return true
        return result - last > 0 && check3(Pair(result - last, arguments.subList(0, arguments.size - 1)))
    }

    private fun checkConcat(equation: Pair<Long, List<Long>>): Boolean{
        var (result, arguments) = equation
        var last = arguments.last()
        while (last > 0){
            if (result % 10 != last % 10) return false //check last digit
            result /= 10 //cut off last digit
            last /= 10
        }
        return result > 0 && check3(Pair(result, arguments.subList(0, arguments.size - 1)))
    }
}