import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readText().trim().lines()

fun readGrid(input: List<String>): Array<Array<Char>> {
    return input.map { line ->
        line.map { char -> char }.toTypedArray()
    }.toTypedArray()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun String.splitByWhitespace() = this.split("\\s+".toRegex())

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
