package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicoDiaFilter implements MedicoFilter {

    private DayOfWeek diaToFilter;

    public MedicoDiaFilter(DayOfWeek diaToFilter) {
        this.diaToFilter = diaToFilter;
    }


    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if(diaToFilter==null)return medicos;
        Set<Medico> filtered=new HashSet<>();
        for(Medico medico:medicos){
            for(WorkTime workTime:medico.getWorkTimes()){
                if(workTime.getDay().equals(this.diaToFilter)){
                    filtered.add(medico);
                    break;
                }
            }
        }
        return filtered;
/*        return medicos.stream()
                .filter(medico -> medico.==this.diaToFilter)
                .collect(Collectors.toSet());
                */
    }
}
