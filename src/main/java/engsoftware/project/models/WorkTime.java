package engsoftware.project.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkTime extends BaseModel  {

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Medico medico;

    private DayOfWeek day;
    private LocalTime start;
    private LocalTime end;

    public WorkTime (DayOfWeek day, LocalTime start, LocalTime end) {
        this.day=day;
        this.start=start;
        this.end=end;
    }
}
