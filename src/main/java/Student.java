import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Student {
    private String fullName;
    private Curriculum curriculum;
    private LocalDateTime startingDate;

    public Student(String name, Curriculum curriculum, LocalDateTime start){
        this.fullName = name;
        this.curriculum = curriculum;
        this.startingDate = start;
    }
}
