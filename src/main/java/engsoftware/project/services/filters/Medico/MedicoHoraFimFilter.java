package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.util.Set;
import java.util.stream.Collectors;

public class MedicoHoraFimFilter implements MedicoFilter {

        private WorkTime horaFimToFilter;

        public MedicoHoraFimFilter(WorkTime horaFimToFilter) {
            this.horaFimToFilter = horaFimToFilter;
        }

        @Override
        public Set<Medico> filter(Set<Medico> medicos) {
            if(horaFimToFilter==null)return medicos;

            return medicos.stream()
                    .filter(medico -> medico.getWorkTimes()==this.horaFimToFilter)
                    .collect(Collectors.toSet());
        }
}
