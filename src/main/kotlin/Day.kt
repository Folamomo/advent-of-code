import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.div
import kotlin.io.path.readLines

abstract class Day(val number: Int) {
    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    fun part1(path: Path): Int = part1(path.readLines())
    fun part2(path: Path): Int = part2(path.readLines())

    val resourcesDir: Path = Path.of("src", "main", "resources")
    fun inputPath() = resourcesDir / "Day%02d.txt".format(number)

    fun part1() = part1(inputPath())
    fun part2() = part2(inputPath())

    fun run(){
        println("Day $number Part 1: ${part1()}")
        println("Day $number Part 2: ${part2()}")
    }
}