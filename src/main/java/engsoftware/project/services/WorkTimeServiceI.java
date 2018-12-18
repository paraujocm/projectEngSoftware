package engsoftware.project.services;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.util.Optional;
import java.util.Set;

public interface WorkTimeServiceI {

    Set<WorkTime> getSetWorkTime();

    Optional<WorkTime> findByDay(WorkTime dayOfWeek);

    Optional<WorkTime> findById(Long id);

    Optional<Medico> findByMedico(String nameMedico);

    Set<WorkTime> findAll();

    WorkTime save(WorkTime especialidade);

    Optional<WorkTime> saveWorkTime(WorkTime workTime, String nameMedico);


}
