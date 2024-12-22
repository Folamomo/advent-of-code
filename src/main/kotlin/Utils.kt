import java.math.BigInteger
import java.nio.file.Path
import java.security.MessageDigest

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

val whitespaceRegex = "\\s+".toRegex()

fun List<String>.toIntPairs() = this.map{it.split(whitespaceRegex)}.map { Pair(it[0].toInt(), it[1].toInt()) }

fun <T1, T2> List<Pair<T1, T2>>.forEachElement(action1: (T1) -> Unit, action2: (T2) -> Unit) {
    this.forEach{
        action1.invoke(it.first)
        action2.invoke(it.second)
    }
}

fun <T> List<T>.without(index: Int) : List<T>{
    val result = this.toMutableList()
    result.removeAt(index)
    return result
}

fun loadAs2dMatrix(input: Path): Array<Array<Char>> {
    return input.toFile().readLines()
        .map(String::toCharArray)
        .map(CharArray::toTypedArray)
        .toTypedArray()
}

fun loadAs2dMatrixOfDigits(input: Path): List<List<Int>> {
    return input.toFile().readLines()
        .map(String::toCharArray)
        .map(CharArray::toTypedArray)
        .map{it.map(Char::digitToInt)}
}

fun <T> pairwise(list: List<T>) = sequence {
    for (left in list.indices){
        for (right in left + 1 ..< list.size){
            yield(Pair(list[left], list[right]))
        }
    }
}

fun GCD(a: Int, b: Int): Int {
    var num1 = a
    var num2 = b
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}