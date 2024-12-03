import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.nio.file.Path
import kotlin.io.path.div

abstract class DayTest(
    private val underTest: Day,
    private val expected1: Int,
    private val expected2: Int
){
    private val testResourcesDir: Path = Path.of("src", "test", "resources")
    private val testInput = testResourcesDir / "Day%02d_test.txt".format(underTest.number)

    @Test
    fun testPart1(){
        assertEquals(expected1, underTest.part1(testInput))
    }

    @Test
    fun testPart2(){
        assertEquals(expected2, underTest.part2(testInput))
    }
}