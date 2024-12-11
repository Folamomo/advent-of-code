import java.nio.file.Path
import kotlin.io.path.readText

class Day9: Day(9) {
    data class File(val id: Int, var size: Int, var freeSpace: Int){
        constructor(id: Int, data: String): this(id, data[id * 2].digitToInt(), data[id * 2 + 1].digitToInt())
        fun next(data: String) = File(id + 1, data)
        fun previous(data: String) = File(id - 1, data)
    }

    override fun part1(input: Path): Long {
        val data = input.readText()
        var leftFile = File(0, data)
        var rightFile = File(data.length / 2, data[data.length - 1].digitToInt(), 0)
        var insertPosition = leftFile.size
        var checksum = 0L //id of left is 0
        while (true) {
            when {
                leftFile.freeSpace > rightFile.size -> {
                    //copy whole right file into free space and move on to previous right file
                    leftFile.freeSpace -= rightFile.size
                    checksum += checkSumRange(rightFile.id, insertPosition, rightFile.size)
                    insertPosition += rightFile.size
                    rightFile = rightFile.previous(data)
                    if (leftFile.id == rightFile.id) {
                        //checksum is already correct
                        break
                    }
                }
                leftFile.freeSpace < rightFile.size -> {
                    //copy as much as can fit, move on to next left file
                    rightFile.size -= leftFile.freeSpace
                    checksum += checkSumRange(rightFile.id, insertPosition, leftFile.freeSpace)
                    insertPosition += leftFile.freeSpace
                    leftFile = leftFile.next(data)
                    if (leftFile.id == rightFile.id) {
                        //calculate checksum of data remaining in right file
                        checksum += checkSumRange(rightFile.id, insertPosition, rightFile.size)
                        break
                    }
                    //calculate checksum of the data in new left file
                    checksum += checkSumRange(leftFile.id, insertPosition, leftFile.size)
                    insertPosition += leftFile.size
                }
                //equals
                else -> {
                    //right fits perfectly, copy all and advance both
                    checksum += checkSumRange(rightFile.id, insertPosition, rightFile.size)
                    insertPosition += rightFile.size
                    leftFile = leftFile.next(data)
                    rightFile = rightFile.previous(data)
                    if (leftFile.id > rightFile.id) { //we may have jumped over
                        //checksum is already correct
                        break
                    }
                    if (leftFile.id == rightFile.id) { //one file remains
                        //calculate checksum of data remaining in right file
                        checksum += checkSumRange(rightFile.id, insertPosition, rightFile.size)
                        break
                    }
                    //calculate checksum of the data in new left file
                    checksum += checkSumRange(leftFile.id, insertPosition, leftFile.size)
                    insertPosition += leftFile.size
                }

            }
        }
        return checksum
    }

    fun checkSumRange(id: Int, from: Int, size: Int): Long {
        return id.toLong() * size.toLong() * (2L * from.toLong() + size.toLong() - 1) / 2L
    }

    data class File2(val id: Int, val size: Int, var freeSpace: Int, val startsAt: Int, var takenSpace: Int = size)

    override fun part2(input: Path): Long {
        val data = input.readText() + "0"
        val files = ArrayList<File2>(data.length / 2)
        var startsAt = 0
        for (i in data.indices step 2) {
            val file = File2(i/2, data[i].digitToInt(), data[i + 1].digitToInt(), startsAt)
            files.add(file)
            startsAt += file.size + file.freeSpace
        }
        var checksum = 0L
        for (fileToMove in files.reversed()) {
            val found = files.subList(0, fileToMove.id).find { it.freeSpace >= fileToMove.size }
            if (found != null) {
                checksum += checkSumRange(fileToMove.id, found.startsAt + found.takenSpace, fileToMove.size)
                found.freeSpace -= fileToMove.size
                found.takenSpace += fileToMove.size
            } else {
                checksum += checkSumRange(fileToMove.id, fileToMove.startsAt, fileToMove.size)
            }
        }
        return checksum
    }
}