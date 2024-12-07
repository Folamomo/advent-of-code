import java.nio.file.Path

class Day4 : Day(4) {
    private val patterns = arrayOf(
        """XMAS""",
        """SAMX""",
        """ 
                |X
                |M
                |A
                |S
            """,
        """
                |S
                |A
                |M
                |X
            """,
        """ 
                |X***
                |*M**
                |**A*
                |***S
            """,
        """
                |S***
                |*A**
                |**M*
                |***X
            """,
        """ 
                |***X
                |**M*
                |*A**
                |S***
            """,
        """ 
                |***S
                |**A*
                |*M**
                |X***
            """,
    ).map(String::trimMargin)
        .map(::stringTo2dMatrix)

    override fun part1(input: Path): Int {
        val data = loadAs2dMatrix(input)
        return patterns.sumOf { countMatches(data, it) }
    }

    private fun stringTo2dMatrix(s: String): Array<Array<Char>> {
        return s.split('\n')
            .map(String::toCharArray)
            .map(CharArray::toTypedArray)
            .toTypedArray()
    }



    private fun countMatches(
        array: Array<Array<Char>>,
        mask: Array<Array<Char>>,
    ): Int {
        var count = 0
        for (i in 0..array.size - mask.size) {
            maskPositionLoop@ for (j in 0..array[0].size - mask[0].size) {
                for (k in mask.indices) {
                    for (l in mask[0].indices) {
                        if (!charMatches(array[i + k][j + l], mask[k][l])) continue@maskPositionLoop
                    }
                }
                count++
            }
        }
        return count
    }

    private fun charMatches(c1: Char, c2: Char): Boolean {
        return c2 == '*' || c1 == c2
    }

    private val patterns2 = arrayOf(
        """
            |M*S
            |*A*
            |M*S
        """,
        """
            |M*M
            |*A*
            |S*S
        """,
        """
            |S*S
            |*A*
            |M*M
        """,
        """
            |S*M
            |*A*
            |S*M
        """,
    ).map(String::trimMargin)
        .map(::stringTo2dMatrix)


    override fun part2(input: Path): Int {
        val data = loadAs2dMatrix(input)
        return patterns2.sumOf { countMatches(data, it) }
    }
}