package main;

public enum DateInterval {
    CLOSED(1) {
        @Override
        public boolean isIn(int d, int left, int right) {
            return d >= left && d <= right;
        }
    }, CLOSED_LEFT(2) {
        @Override
        public boolean isIn(int d, int left, int right) {
            return d >= left && d < right;
        }
    }, CLOSED_RIGHT(2) {
        @Override
        public boolean isIn(int d, int left, int right) {
            return d > left && d <= right;
        }
    }, OPEN(3) {
        @Override
        public boolean isIn(int d, int left, int right) {
            return d > left && d < right;
        }
    };
    public final int index;

    DateInterval(int index){
        this.index = index;
    }

    public abstract boolean isIn(int d, int left, int right);
}
