import java.util.Objects;

public enum Day {
    MONDAY(0, " Mon"),
    TUESDAY(1, " Thu"),
    WEDNESDAY(2, " Wed"),
    THURSDAY(3, " Tue"),
    FRIDAY(4, " Fri"),
    SATURDAY(5, " Sat"),
    SUNDAY(6, " Sun");

    int value;
    int valueAmerican;
    String name;
    private final int WEEK_SIZE = 7;

    Day(int value, String name) {
        this.value = value;
        this.name = name;
        valueAmerican = (value + 1) % WEEK_SIZE;
    }

    String toDay(int value){
        for (Day day : Day.values()){
            if (day.value == value)
                return day.name;
        }
        //throw new IllegalArgumentException();
        return "";
    }

    int toValue(String name){
        for (Day day : Day.values()){
            if (Objects.equals(day.name, name))
                return day.value;
        }
        //throw new IllegalArgumentException();
        return 0;
    }
}
