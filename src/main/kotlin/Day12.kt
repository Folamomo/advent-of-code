import java.nio.file.Path
import java.util.*

class Day12: Day(12) {
    override fun part1(input: Path): Any {
        val data = loadAs2dMatrix(input)
        val toVisit: Queue<Pair<Int, Int>> = ArrayDeque<Pair<Int, Int>>()
        toVisit.add(Pair(0,0))
        var totalCost = 0
        while (toVisit.isNotEmpty()) {
            val position = toVisit.remove()
            val (y, x) = position
            val color = data[y][x]
            if (color.isLowerCase()) continue
            totalCost += visitOneArea(position, data, toVisit)
        }
        return totalCost
    }

    private fun visitOneArea(startAt: Pair<Int, Int>, data: Array<Array<Char>>, putFoundAreasHere: Queue<Pair<Int, Int>>): Int {
        val sameAreaToVisit: Queue<Pair<Int, Int>> = ArrayDeque<Pair<Int, Int>>()
        sameAreaToVisit.add(startAt)

        var fenceCount = 0
        var areaCount = 0
        val c = data[startAt.first][startAt.second]

        while (sameAreaToVisit.isNotEmpty()) {
            val position = sameAreaToVisit.remove()
            val (y, x) = position
            val color = data[y][x]
            if (color.isLowerCase()) continue
            data[y][x] = color.lowercaseChar()//mark as visited
            areaCount++
            for (n in neighbours(position)){
                if (!isInBounds(data, n)){
                    fenceCount++
                    continue
                }
                val (ny, nx) = n
                val nColor = data[ny][nx]
                if (nColor == color){
                    sameAreaToVisit.add(n)
                    continue
                }
                if (nColor == color.lowercaseChar()){ //neighbour is in the same area but was already checked
                    continue
                }
                fenceCount++
                if (nColor.isUpperCase()){
                    putFoundAreasHere.add(n)
                }
            }
        }
//        println("A region of $c plants with price $areaCount * $fenceCount = ${areaCount * fenceCount}.")
        return fenceCount * areaCount
    }

    private fun isInBounds(map: Array<Array<Char>>, position: Pair<Int, Int>): Boolean {
        val (y, x) = position
        return y >= 0 && y < map.size && x >= 0 && x < map[0].size
    }


    private fun neighbours(p: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (y, x) = p
        return listOf(
            Pair(y - 1, x),
            Pair(y + 1, x),
            Pair(y , x - 1),
            Pair(y , x + 1),
        )
    }

    override fun part2(input: Path): Any {
        TODO("Not yet implemented")
    }
}