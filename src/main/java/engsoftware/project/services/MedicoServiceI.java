package engsoftware.project.services;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;
import engsoftware.project.services.filters.Medico.FilterObjectMedico;

import java.util.Optional;
import java.util.Set;

public interface MedicoServiceI {

    Set<Medico> getSetMedico();

    Set<Medico> getFilteredMedico(FilterObjectMedico filterObjectMedico);

    Set<Medico> findAll();

    Optional<Medico> findById(Long id);

    Optional<Medico> findByName(String nameMedico);

    Medico save(Medico medico);

    Optional<Medico> saveMedico (String nr_utente_saude);

    Boolean isAvailable (String nameMedico, WorkTime workTime);
}
