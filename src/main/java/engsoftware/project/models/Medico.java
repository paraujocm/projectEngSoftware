package engsoftware.project.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends BaseModel  {

    private String nome;
    private String email;
    private String nrTelemovel;


    @OneToMany(cascade = CascadeType.MERGE,orphanRemoval = true,mappedBy = "medico")
    private Set<Especialidade> especialidades=new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE,orphanRemoval = true,mappedBy = "medico")
    private Set<Consulta> consultas=new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE,orphanRemoval = true,mappedBy = "medico")
    private Set<WorkTime> workTimes=new HashSet<>();

    public Medico(String nome, String email, String nrTelemovel, Especialidade especialidade) {
        this.nome=nome;
        this.email=email;
        this.nrTelemovel=nrTelemovel;
        this.addEspecialidadeeToMedico(especialidade);
    }

    // add consulta a um medico
    public boolean addConsutaToMedico(Consulta consulta){
        if(trabalha(consulta.getHorario()) && disponivel(consulta.getHorario()))
        {
            consultas.add(consulta);
            consulta.setMedico(this);
            return true;
        }
        else{
            return false;
        }
    }

    // add worktime a um medico
    public void addEspecialidadeeToMedico(Especialidade especialidade){
        especialidades.add(especialidade);
        especialidade.setMedico(this);
    }

    // add worktime a um medico
    public void addWorkTimeToMedico(WorkTime workTime){
        workTimes.add(workTime);
        workTime.setMedico(this);
    }

    public void removeWorkTimeFromMedico(WorkTime workTime){
        workTimes.remove(workTime);
    }

    public void addMedico(Medico medico){
        medico.addMedico(medico);
    }

    public void removeMedico(Medico medico){
        medico.removeMedico(medico);
    }

    public boolean trabalha (LocalDateTime dataConsulta){
        for(WorkTime horarioActual:this.getWorkTimes()){
            if(horarioActual.getDay().equals(dataConsulta.getDayOfWeek())){
                if (horarioActual.getStart().isBefore(dataConsulta.toLocalTime())){
                    if (horarioActual.getEnd().isAfter(dataConsulta.toLocalTime())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean disponivel (LocalDateTime dataConsulta){
        for(Consulta consultaMedico:this.getConsultas()){
            if(consultaMedico.getHorario().getDayOfWeek().equals(dataConsulta.getDayOfWeek())){
                if (consultaMedico.getHorario().equals(dataConsulta)) {
                    return false;
                }
                if(consultaMedico.getFimExpectavel().toLocalTime().isAfter(dataConsulta.toLocalTime()))
                {
                    return false;
                }
                if(consultaMedico.getHorario().toLocalTime().isBefore(dataConsulta.toLocalTime().plusMinutes(30)))
                {
                    return false;
                }
            }
        }
        return true;
    }

}
