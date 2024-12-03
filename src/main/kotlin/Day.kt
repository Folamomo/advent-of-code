import java.nio.file.Path
import kotlin.io.path.div

abstract class Day(val number: Int) {
    abstract fun part1(input: Path): Int
    abstract fun part2(input: Path): Int

    private val resourcesDir: Path = Path.of("src", "main", "resources")
    val inputPath: Path = resourcesDir / "Day%02d.txt".format(number)

    fun part1() = part1(inputPath)
    fun part2() = part2(inputPath)

    fun run(){
        println("Day $number Part 1: ${part1()}")
        println("Day $number Part 2: ${part2()}")
    }
}