package ranges

import com.carrotsearch.junitbenchmarks.AbstractBenchmark
import org.junit.Test
import java.util.Random


class RangesBenchmark : AbstractBenchmark() {

    private fun generateRanges(howMany: Int): List<Range> {
        val rnd = Random()
        val res = arrayListOf<Range>()
        for (i in 1..howMany) {
            val a = rnd.nextInt()
            val b = rnd.nextInt()
            res.add(Range(Math.min(a,b), Math.max(a,b)))
        }
        return res
    }

    private val list = generateRanges(1000000)
    private val listForAndrey = list.map { Andrey.Range(it.from, it.to) }

    Test fun dimas() {
        merge(list)
    }

    Test fun andreys() {
        Andrey.merge(listForAndrey)
    }
}