package data;

import java.time.DayOfWeek;

public enum WeekLayout {
    STANDARD(0) {
        @Override
        public DayOfWeek[] header() {
            return new DayOfWeek[]{
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY,
                    DayOfWeek.SATURDAY,
                    DayOfWeek.SUNDAY};
        }

        @Override
        public boolean isWeekend(DayOfWeek dayWeek) {
            return dayWeek == DayOfWeek.SATURDAY || dayWeek == DayOfWeek.SUNDAY;
        }

        @Override
        public DayOfWeek getStartDayOfWeek() {
            return DayOfWeek.MONDAY;
        }
    },
    AMERICAN(1) {
        @Override
        public DayOfWeek[] header() {
            return new DayOfWeek[]{DayOfWeek.SUNDAY,
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY,
                    DayOfWeek.SATURDAY};
        }

        @Override
        public boolean isWeekend(DayOfWeek dayWeek) {
            return dayWeek == DayOfWeek.SUNDAY;
        }

        @Override
        public DayOfWeek getStartDayOfWeek() {
            return DayOfWeek.SUNDAY;
        }
    };

    public final int OFFSET_RELATIVE_OF_STANDARD;

    WeekLayout(int OFFSET_RELATIVE_OF_STANDARD) {
        this.OFFSET_RELATIVE_OF_STANDARD = OFFSET_RELATIVE_OF_STANDARD;
    }

    public abstract DayOfWeek[] header();
    public abstract boolean isWeekend(DayOfWeek dayWeek);
    public abstract DayOfWeek getStartDayOfWeek();
}