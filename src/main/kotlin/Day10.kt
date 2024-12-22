import java.nio.file.Path
import java.util.*

class Day10: Day(10) {
    override fun part1(input: Path): Int {
        val map = loadAs2dMatrixOfDigits(input)
        var sum = 0
        for (y in map.indices) {
            for (x in map[0].indices) {
                if (map[y][x] == 0) {
                    sum += findTrails(map, y, x)
                }
            }
        }
        return sum
    }

    fun getNeighbours(y: Int, x: Int, map: List<List<Int>>): List<Pair<Int, Int>> {
        val maxY = map.size - 1
        val maxX = map[0].size - 1
        return listOf(
            Pair(y, x + 1),
            Pair(y, x - 1),
            Pair(y + 1, x),
            Pair(y - 1, x),
        ).filter { (y, x) -> y in 0..maxY && x in 0 .. maxX }
    }

    private fun findTrails(map: List<List<Int>>, y: Int, x: Int): Int {
        val visited: List<MutableList<Boolean>> = List<MutableList<Boolean>>(map.size){MutableList<Boolean>(map[0].size){false}}
        val toVisit = Stack<Pair<Int, Int>>()
        toVisit.push(Pair(y, x))
        var score = 0
        while (toVisit.isNotEmpty()) {
            val current = toVisit.pop()
            visited[current.first][current.second] = true
            val height = map[current.first][current.second]
            if (height == 9) {
                score ++
                continue
            }
            for (it in getNeighbours(current.first, current.second, map)) {
                val (ny, nx) = it
                if(!visited[ny][nx] && map[ny][nx] == height + 1){
                    toVisit.push(it)
                }
            }
        }
        return score
    }

    override fun part2(input: Path): Int {
        val map = loadAs2dMatrixOfDigits(input)
        var sum = 0
        for (y in map.indices) {
            for (x in map[0].indices) {
                if (map[y][x] == 0) {
                    sum += findTrails2(map, y, x)
                }
            }
        }
        return sum
    }

    private fun findTrails2(map: List<List<Int>>, y: Int, x: Int): Int {
        val toVisit = Stack<Pair<Int, Int>>()
        toVisit.push(Pair(y, x))
        var score = 0
        while (toVisit.isNotEmpty()) {
            val current = toVisit.pop()
            val height = map[current.first][current.second]
            if (height == 9) {
                score ++
                continue
            }
            for (it in getNeighbours(current.first, current.second, map)) {
                val (ny, nx) = it
                if(map[ny][nx] == height + 1){
                    toVisit.push(it)
                }
            }
        }
        return score
    }

}