import java.nio.file.Path
import kotlin.io.path.div

class DayTest(
    private val underTest: Day,
    private val expected1: Int,
    private val expected2: Int
){
    val testResourcesDir: Path = Path.of("src", "test", "resources")

    fun doTest(){
        val testInput = testResourcesDir / "Day%02d_test.txt".format(underTest.number)
        assert(expected1 == underTest.part1(testInput))
        assert(expected2 == underTest.part2(testInput))
    }
}