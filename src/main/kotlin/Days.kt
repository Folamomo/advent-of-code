enum class Days(val day: Day) {
    DAY1(Day1()),
    DAY2(Day2()),
    DAY3(Day3()),
    DAY4(Day4()),
    DAY5(Day5()),
    DAY6(Day6()),
    DAY7(Day7()),
    ;
    fun run() = day.run()
}