import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class Student {
    private String fullName;
    private Curriculum curriculum;
    private Date startingDate;

    public Student(String name, Curriculum curriculum, Date start){
        this.fullName = name;
        this.curriculum = curriculum;
        this.startingDate = start;
    }
}
