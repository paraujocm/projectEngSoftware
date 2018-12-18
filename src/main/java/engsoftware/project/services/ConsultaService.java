package engsoftware.project.services;


import engsoftware.project.models.Consulta;
import engsoftware.project.models.Medico;
import engsoftware.project.models.Paciente;
import engsoftware.project.models.WorkTime;
import engsoftware.project.repositories.ConsultaRepoI;
import engsoftware.project.repositories.MedicoRepoI;
import engsoftware.project.repositories.PacienteRepoI;
import engsoftware.project.services.filters.Consulta.FilterObjectConsulta;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ConsultaService implements ConsultaServiceI {

    private ConsultaFilterService consultaFilterService;

    private ConsultaRepoI consultaRepoI;

    private MedicoRepoI medicoRepoI;

    private PacienteRepoI pacienteRepoI;

    private WorkTimeServiceI workTimeRepoI;

    public ConsultaService(ConsultaFilterService consultaFilterService, ConsultaRepoI consultaRepoI, MedicoRepoI medicoRepoI, PacienteRepoI pacienteRepoI, WorkTimeServiceI workTimeRepoI) {
        this.consultaFilterService = consultaFilterService;
        this.consultaRepoI = consultaRepoI;
        this.medicoRepoI = medicoRepoI;
        this.pacienteRepoI = pacienteRepoI;
        this.workTimeRepoI = workTimeRepoI;
    }

    @Override
    public Set<Consulta> getSetConsulta() {
        Set<Consulta> consultas = new HashSet<>();
        for (Consulta consulta : this.consultaRepoI.findAll()) {
            consultas.add(consulta);
        }
        return consultas;
    }

    @Override
    public Set<Consulta> getFilteredConsulta(FilterObjectConsulta filterObjectConsulta) {
        return consultaFilterService.filterConsultas(findAll(), filterObjectConsulta);
    }


    @Override
    public Set<Consulta> findAll() {
        Set<Consulta> consultas = new HashSet<>();
        for (Consulta consulta : this.consultaRepoI.findAll()) {
            consultas.add(consulta);
        }
        return Collections.unmodifiableSet(consultas);
    }

    @Override
    public Optional<Medico> findByName(String nameMedico) {
        return medicoRepoI.findByName(nameMedico);
    }

    @Override
    public Optional<Paciente> findByNr_utente_saude(String nr_utente_saude) {
        return pacienteRepoI.findByNr_utente_saude(nr_utente_saude);
    }

    @Override
    public Optional<Consulta> findById(Long id) {

        return consultaRepoI.findById(id);
        /*if(courseOptional.isPresent()){
            return Optional.of(courseOptional.get().getDTO());
        }
        return Optional.empty();*/
    }

    @Override
    public Consulta save(Consulta consulta) {
        return this.consultaRepoI.save(consulta);
    }

    @Override
    public Optional<Consulta> saveConsulta(Medico medico, Consulta consulta, String nr_utente_saude) {
        Optional<Paciente> pacienteOptional = this.pacienteRepoI.findByNr_utente_saude(nr_utente_saude);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            if (medico.addConsutaToMedico(consulta)) {
                paciente.addConsutaToPaciente(consulta);
                pacienteRepoI.save(paciente);
                //jhgavfbkdf
                return consultaRepoI.findById(consulta.getId());
            }

        }
        return Optional.empty();
    }


}

