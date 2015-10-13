package data;

import java.util.Calendar;

public enum WeekLayout {
    STANDARD(0) {
        @Override
        public int[] header() {
            return new int[]{Calendar.MONDAY, Calendar.THURSDAY, Calendar.WEDNESDAY, Calendar.TUESDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY};
        }

        @Override
        public boolean isWeekend(int dayWeek) {
            return dayWeek == Calendar.SATURDAY || dayWeek == Calendar.SUNDAY;
        }
    },
    AMERICAN(1) {
        @Override
        public int[] header() {
            return new int[]{Calendar.SUNDAY, Calendar.MONDAY, Calendar.THURSDAY, Calendar.WEDNESDAY, Calendar.TUESDAY, Calendar.FRIDAY, Calendar.SATURDAY};
        }

        @Override
        public boolean isWeekend(int dayWeek) {
            return dayWeek == Calendar.SUNDAY || dayWeek == Calendar.SUNDAY;
        }
    };

    public int OFFSET_RELATIVE_OF_STANDARD;

    WeekLayout(int OFFSET_RELATIVE_OF_STANDARD) {
        this.OFFSET_RELATIVE_OF_STANDARD = OFFSET_RELATIVE_OF_STANDARD;
    }

    public abstract int[] header();
    public abstract boolean isWeekend(int dayWeek);
}