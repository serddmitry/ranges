package ranges

import java.util.ArrayList


data class Range(val from: Int, val to: Int) {
    fun overlaps(other: Range) = Math.min(to, other.to) >= Math.max(from, other.from)
    fun merge(other: Range) = Range(Math.min(from, other.from), Math.max(to, other.to))
}

fun merge(ranges: List<Range>): Collection<Range> {
    if (ranges.size <= 1) return ranges // list of 0 and 1 range is already merged

    val queue = ranges.sortBy {it.from }.toLinkedList()
    val result = arrayListOf<Range>()
    while (queue.notEmpty) {
        val a = queue.removeFirst() // never fails since it's not empty
        val b = queue.poll() // works just like removeFirst but may return null
        when {
            b == null -> result.add(a)
            a.overlaps(b) -> queue.addFirst(a.merge(b))
            else -> {
                result.add(a)
                queue.addFirst(b)
            }
        }
    }
    return result
}

fun main(args: Array<String>) {
    val result = merge(listOf(Range(1,2), Range(2,5), Range(-10, 100),
            Range(500, Integer.MAX_VALUE), Range(-13, -10), Range(-16, -14)))
    print(result)
}