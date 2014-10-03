package ranges;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Andrey {

    public static List<Range> merge(List<Range> ranges) {
        List<Range> res = new ArrayList<>();
        Map<Integer, RangeBorder> m = new TreeMap<>();
        for (Range r : ranges) {
            RangeBorder beginBorder = m.get(r.begin);
            if (beginBorder == null) {
                beginBorder = new RangeBorder();
                beginBorder.setBegins(1);
            } else {
                beginBorder.setBegins(beginBorder.getBegins() + 1);
            }
            m.put(r.begin, beginBorder);

            RangeBorder endBorder = m.get(r.end);
            if (endBorder == null) {
                endBorder = new RangeBorder();
                endBorder.setEnds(1);
            } else {
                endBorder.setEnds(endBorder.getEnds() + 1);
            }
            m.put(r.end, endBorder);
        }

        int begins = 0;
        Range currentRange = new Range(0, 0);
        for (int i : m.keySet()) {
            RangeBorder rangeBorder = m.get(i);
            if (begins == 0 && begins + rangeBorder.begins != 0) {
                currentRange = new Range(i, 0);
            }
            if (begins != 0 && begins + rangeBorder.begins - rangeBorder.ends == 0) {
                currentRange.end = i;
                res.add(currentRange);
            }
            begins += rangeBorder.begins - rangeBorder.ends;
        }

        return res;

    }

    public static class RangeBorder {

        private int begins;
        private int ends;

        public int getBegins() {
            return begins;
        }

        public void setBegins(int begins) {
            this.begins = begins;
        }

        public int getEnds() {
            return ends;
        }

        public void setEnds(int ends) {
            this.ends = ends;
        }
    }

    static class Range {

        int begin;
        int end;

        public Range(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        public boolean overlaps(Range that) {
            Range left;
            Range right;
            if (this.begin <= that.begin) {
                left = this;
                right = that;
            } else {
                left = that;
                right = this;
            }
            return left.end >= right.begin;
        }

        public Range merge(Range that) {
            return new Range(Math.min(this.begin, that.begin), Math.max(this.end, that.end));
        }

        @Override
        public String toString() {
            return "Range{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }
}
