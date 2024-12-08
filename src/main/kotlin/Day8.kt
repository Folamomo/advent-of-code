import java.nio.file.Path
import kotlin.io.path.readLines

class Day8: Day(8) {
    override fun part1(input: Path): Any {
        val map = RadarMap(input.readLines())
        return map.countAntinodes()
    }

    data class Point(val y: Int, val x: Int){
        operator fun plus(v: Vector): Point {
            return Point(y + v.y, x + v.x)
        }

        operator fun minus(v: Vector): Point {
            return Point(y - v.y, x - v.x)
        }
    }

    data class Vector(val y: Int, val x: Int){
        constructor(from: Point, to: Point): this(to.y - from.y, to.x - from.x)

        fun reduced(): Vector {
            val gcd = GCD(y, x)
            return Vector(y/gcd, x/gcd)
        }
    }



    data class RadarMap(
        val height: Int,
        val width: Int,
        val antennas: HashMap<Char, ArrayList<Point>>
    ){
        constructor(lines: List<String>): this(lines.size, lines[0].length,
            HashMap<Char, ArrayList<Point>>()){
            for ((y, line) in lines.withIndex()){
                for ((x, c) in line.withIndex()){
                    if (c != '.'){
                        antennas.getOrPut(c, ::ArrayList).add(Point(y, x))
                    }
                }
            }
        }

        private fun makeAntinodes(points: Pair<Point, Point>): List<Point>{
            val (p1, p2) = points
//            println("$p1 $p2")
            val v = Vector(p1, p2)
            return listOf(p1 - v, p2 + v)
        }

        private fun makeAntinodes2(points: Pair<Point, Point>): List<Point>{
            val (p1, p2) = points
            val v = Vector(p1, p2).reduced()
            val result = ArrayList<Point>()
            var antinode = p1
            while (inbounds(antinode)) {
                result.add(antinode)
                antinode += v
            }
            antinode = p1 - v
            while (inbounds(antinode)) {
                result.add(antinode)
                antinode -= v
            }
            return result
        }

        private fun inbounds(point : Point): Boolean{
            return point.x in 0..<width && point.y in 0 ..< height
        }

        fun countAntinodes(): Int {
            return antennas.values
                .asSequence()
                .flatMap { pairwise(it) }
                .flatMap (::makeAntinodes)
                .filter(::inbounds)
                .toSet()
                .count()
        }

        fun countAntinodes2(): Int {
            return antennas.values
                .asSequence()
                .flatMap { pairwise(it) }
                .flatMap (::makeAntinodes2)
                .toSet()
                .count()
        }
    }




    override fun part2(input: Path): Any {
        val map = RadarMap(input.readLines())
        return map.countAntinodes2()
    }
}