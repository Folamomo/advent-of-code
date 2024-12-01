enum class DayTests(val test: DayTest) {
    DAY1_TEST(DayTest(Day1(), 11, 31));
    fun doTest() = test.doTest()
}