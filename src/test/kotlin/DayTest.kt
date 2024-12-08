import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Path
import kotlin.io.path.div

abstract class DayTest(
    private val underTest: Day,
    private val expected1: Any,
    private val expected2: Any
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