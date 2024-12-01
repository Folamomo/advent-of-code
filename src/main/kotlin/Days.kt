enum class Days(val day: Day) {
    DAY1(Day1());
    fun run() = day.run()
}