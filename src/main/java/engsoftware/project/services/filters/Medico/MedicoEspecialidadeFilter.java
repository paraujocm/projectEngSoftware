package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class MedicoEspecialidadeFilter implements MedicoFilter {

   /* private String especialidadeToFilter;

    public MedicoEspecialidadeFilter(String especialidadeToFilter) {
        this.especialidadeToFilter = especialidadeToFilter;
    } */

    private Especialidade especialidadeToFilter;

    public MedicoEspecialidadeFilter(Especialidade especialidadeToFilter) {
        this.especialidadeToFilter = especialidadeToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(especialidadeToFilter==null)return medicos;

        return medicos.stream()
                .filter(medico -> medico.getEspecialidades()==this.especialidadeToFilter)
                .collect(Collectors.toSet());
    }

}
