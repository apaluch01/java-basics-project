import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Curriculum {
    private String title;
    private ArrayList<Course> courses;
    private int hours;

    public Curriculum(CurriculumBuilder builder){
        this.title = builder.title;
        this.courses = builder.courses;
        this.hours = getFullDuration(courses);
    }

    //public Curriculum(String title, ArrayList<Course> courses){
      //  this.title = title;
        //this.courses = courses;
        //this.hours = getFullDuration(courses);
    //}
    
    private int getFullDuration(ArrayList<Course> courses){
        int sum = 0;

        for (Course course : courses) {
            sum += course.getHours();
        }

        return sum;
    }

    @Getter
    public static class CurriculumBuilder{
        private String title;
        private ArrayList<Course> courses = new ArrayList<Course>();
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
