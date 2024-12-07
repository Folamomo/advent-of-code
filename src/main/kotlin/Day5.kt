import java.nio.file.Path
import java.util.Collections
import java.util.HashMap
import kotlin.io.path.readLines

class Day5: Day(5) {
    override fun part1(input: Path): Int {
        val (rules, books) = readInput(input)
        return books.filter{checkBook(it, rules)}
//            .map{println(it); it}
            .sumOf(::getMiddle)
    }

    private fun checkBook(book: List<Int>, rules: HashMap<Int, ArrayList<Int>>): Boolean{
        val pagesFound = HashSet<Int>(book.size)
        for (page in book){
            val applicableRules = rules.getOrDefault(page, listOf())
            if (applicableRules.any {
                pagesFound.contains(it)
            }) return false
            pagesFound.add(page)
        }
        return true
    }

    private fun getMiddle(list: List<Int>) = list[list.size / 2]

    private fun readInput(input: Path): Pair<HashMap<Int, ArrayList<Int>>, ArrayList<List<Int>>> {
        val lines = input.readLines().iterator()
        val rules = HashMap<Int, ArrayList<Int>>()

        for (line in lines) {
            if (line.isEmpty()) break
            val split = line.split("|")
            rules
                .getOrPut(split[0].toInt()){ArrayList()}
                    .add(split[1].toInt())
        }

        val books = ArrayList<List<Int>>()
        for (line in lines) {
            books.add(line.split(",").map(String::toInt).toList())
        }
        return Pair(rules, books)
    }

    override fun part2(input: Path): Int {
        val (rules, books) = readInput(input)
        return books.filter{!checkBook(it, rules)}
            .map { fixBook(it, rules) }
            .sumOf(::getMiddle)
    }

    private fun fixBook(pages: List<Int>, rules: HashMap<Int, ArrayList<Int>>): List<Int> {
        val result = pages.toMutableList()
        for (i in result.size - 1 downTo  0) {
            var j = i - 1
            while (j >= 0){
                if (rules.getOrDefault(result[i], ArrayList()).contains(result[j])) {
                    Collections.swap(result, i ,j)
                    j = i - 1
                } else j--
            }
        }
        return result
    }
}