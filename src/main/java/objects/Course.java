package objects;

import lombok.Getter;

@Getter
public class Course {
    private String title;
    private int hours;

    public Course(String title, int hours) {
        this.title = title;
        this.hours = hours;
    }
}