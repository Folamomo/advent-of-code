enum class Days(val day: Day) {
    DAY1(Day1()),
    DAY2(Day2()),
    ;
    fun run() = day.run()
}