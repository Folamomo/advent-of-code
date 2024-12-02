enum class DayTests(val test: DayTest) {
    DAY1_TEST(DayTest(Day1(), 11, 31)),
    DAY2_TEST(DayTest(Day2(), 2, 4)),
    ;
    fun doTest() = test.doTest()
}