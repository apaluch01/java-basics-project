package com.griddynamics.qa.course.calculator.objects;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Curriculum {

    private String title;
    private List<Course> courses;
    private int hours;

    public Curriculum(CurriculumBuilder builder) {
        this.title = builder.title;
        this.courses = builder.courses;
        this.hours = getFullDuration(courses);
    }
    
    private int getFullDuration(List<Course> courses) {
        int sum = 0;

        for (Course course : courses) {
            sum += course.getHours();
        }

        return sum;
    }

    @Getter
    public static class CurriculumBuilder {
        private String title;
        private List<Course> courses = new ArrayList<>();
        private int hours;

        public CurriculumBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public CurriculumBuilder courses(final Course course) {
            this.courses.add(course);
            return this;
        }

        public CurriculumBuilder hours(final int hours) {
            this.hours = hours;
            return this;
        }

        public Curriculum build() {
            return new Curriculum(this);
        }
    }
}
