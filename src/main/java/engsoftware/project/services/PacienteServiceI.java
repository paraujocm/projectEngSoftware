package engsoftware.project.services;

import engsoftware.project.models.Paciente;
import engsoftware.project.services.filters.Paciente.FilterObjectPaciente;

import java.util.Optional;
import java.util.Set;

public interface PacienteServiceI {

    Set<Paciente> getSetPaciente();

    Set<Paciente> getFilteredPaciente(FilterObjectPaciente filterObjectPaciente);

    Set<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    Optional<Paciente> findByNr_utente_saude(String nr_utente_saude);

    Paciente save(Paciente paciente);

    Optional<Paciente> savePaciente (String nr_utente_saude);
}
