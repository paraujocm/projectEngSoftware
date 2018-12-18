package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.util.Set;
import java.util.stream.Collectors;

public class MedicoHoraInicioFilter implements MedicoFilter {

    private WorkTime horaInicioToFilter;

    public MedicoHoraInicioFilter(WorkTime horaInicioToFilter) {
        this.horaInicioToFilter = horaInicioToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(horaInicioToFilter==null)return medicos;

        return medicos.stream()
                .filter(medico -> medico.getWorkTimes()==this.horaInicioToFilter)
                .collect(Collectors.toSet());
    }
}
