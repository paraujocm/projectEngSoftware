package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicoHoraFimFilter implements MedicoFilter {

    private LocalTime horaFimToFilter;

    public MedicoHoraFimFilter(LocalTime horaFimToFilter) {
        this.horaFimToFilter = horaFimToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (horaFimToFilter == null) return medicos;

/*            return medicos.stream()
                    .filter(medico -> medico.getWorkTimes().equals(this.horaFimToFilter))
                    .collect(Collectors.toSet());

        */
        Set<Medico> filteredMedicos = new HashSet<>();
        for (Medico medico : medicos) {
            for(WorkTime workTime:medico.getWorkTimes()){
                if(workTime.getEnd().isBefore(this.horaFimToFilter)){
                    filteredMedicos.add(medico);
                }
            }
        }
        return filteredMedicos;
    }

}
