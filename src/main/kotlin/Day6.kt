import java.nio.file.Path

class Day6: Day(6) {
    override fun part1(input: Path): Int {
        val map = loadAs2dMatrix(input)
        return solve(map)
    }

    enum class Direction(val symbol: Char){
        UP('^'),
        RIGHT('>'),
        DOWN('v'),
        LEFT('<');
        fun turnRight() = when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }

        fun turnLeft() = when (this) {
            UP -> LEFT
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
        }

        fun move(position: Pair<Int, Int>): Pair<Int, Int> {
            val (y, x) = position
            return when (this) {
                UP -> Pair(y - 1, x)
                RIGHT -> Pair(y, x + 1)
                DOWN -> Pair(y + 1, x)
                LEFT -> Pair(y, x - 1)
            }
        }

        fun moveBackwards(position: Pair<Int, Int>): Pair<Int, Int> {
            val (y, x) = position
            return when (this) {
                UP -> Pair(y + 1, x)
                RIGHT -> Pair(y, x - 1)
                DOWN -> Pair(y - 1, x)
                LEFT -> Pair(y, x + 1)
            }
        }
        companion object {
            val symbols = entries.map{it.symbol}.toList()
        }
    }


    private fun findStart(map: Array<Array<Char>>): Pair<Int, Int> {
        for(y in map.indices) {
            for(x in map[0].indices) {
                if (map[y][x] == '^') {
                    return Pair(y, x)
                }
            }
        }
        throw IllegalStateException("No solution")
    }

    private fun isInBounds(map: Array<Array<Char>>, position: Pair<Int, Int>): Boolean {
        val (y, x) = position
        return y >= 0 && y < map.size && x >= 0 && x < map[0].size
    }

    private operator fun Array<Array<Char>>.get(position: Pair<Int, Int>) = this[position.first][position.second]
    private operator fun Array<Array<Char>>.set(position: Pair<Int, Int>, c: Char){this[position.first][position.second] = c}

        private fun solve(map: Array<Array<Char>>): Int {
        var position = findStart(map)
        var direction = Direction.UP
        var counter = 1
        map[position] = 'X'
        while (true) {
            val nextPosition = direction.move(position)
            when{
                !isInBounds(map, nextPosition) -> return counter
                map[nextPosition] == '#' -> direction = direction.turnRight()
                map[nextPosition] == '.' -> {
                    map[nextPosition] = 'X'
                    counter++
                    position = nextPosition
                }
                map[nextPosition] == 'X' -> position = nextPosition
            }
        }
    }

    override fun part2(input: Path): Int {
        val map = loadAs2dMatrix(input)
        return solve2(map)
    }

    private fun solve2(map: Array<Array<Char>>): Int {
        var position = findStart(map)
        var direction = Direction.UP
        var counter = 0
        while (true) {
            val nextPosition = direction.move(position)
            when{
                !isInBounds(map, nextPosition) -> return counter
                map[nextPosition] == '#' -> {
                    direction = direction.turnRight()
                }
                map[nextPosition] == '.' -> {
                    counter += tryBarrel(map, position, direction)
                    map[position] = direction.symbol
                    position = nextPosition
                }
                Direction.symbols.contains(map[nextPosition]) -> {
                    //barrel at this position was already tried
                    map[position] = direction.symbol
                    position = nextPosition
                }
                else -> throw IllegalArgumentException("Map contains Char ${map[position]} at y: ${position.first}, x: ${position.second}")
            }
        }
    }

    //place barrel ahead and check for loops
    private fun tryBarrel(oldMap: Array<Array<Char>>, startingPosition: Pair<Int, Int>, startingDirection: Direction): Int {
        val map = Array(oldMap.size) { oldMap[it].clone() }
        var position = startingPosition
        var direction = startingDirection
        map[direction.move(position)] = '#'
        while (true) {
            val nextPosition = direction.move(position)
            when{
                !isInBounds(map, nextPosition) -> {
                    return 0
                }
                map[nextPosition] == '#' -> {
                    direction = direction.turnRight()
                }
                else -> {
                    if(map[position] == direction.symbol){
                        return 1
                    }
                    map[position] = direction.symbol
                    position = nextPosition
                }
            }
        }
    }

}