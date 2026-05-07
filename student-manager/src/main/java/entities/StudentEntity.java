package entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String fullName;
    private String email;
    private String matricNumber;
    private LocalDateTime enrolledAt;

    @ManyToMany
    @JoinTable(

            name = "enrollment", joinColumns=@JoinColumn(name ="studentId"),
            inverseJoinColumns = @JoinColumn(name ="courseId")
    )
    private List <CourseEntity> courseList;

}
