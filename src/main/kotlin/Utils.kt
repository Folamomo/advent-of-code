import java.math.BigInteger
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

