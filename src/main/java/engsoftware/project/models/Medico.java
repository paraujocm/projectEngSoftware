package engsoftware.project.models;

import lombok.*;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "medico")
    private Set<Especialidade> especialidades=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "medico")
    private Set<Consulta> consultas=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "medico")
    private Set<WorkTime> workTimes=new HashSet<>();

    public Medico(String nome, String email, String nr_telemovel, Especialidade especialidade) {
        this.nome=nome;
        this.email=email;
        this.nrTelemovel=nr_telemovel;
        this.addEspecialidade(especialidade);
    }

    // add especialidade ao medico
    public void addEspecialidade(Especialidade especialidade){
        especialidades.add(especialidade);
        especialidade.setMedico(this);
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
    public void addWorkTimeToMedico(WorkTime workTime){
        workTimes.add(workTime);
        workTime.setMedico(this);
    }

    public void removeEspecialidadeFromMedico(Especialidade especialidade){
        especialidades.remove(especialidade);
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

    public boolean trabalha (WorkTime workTime){
        for(WorkTime workTimeMedico:this.getWorkTimes()){
            if(workTimeMedico.getDay().equals(workTime.getDay())){
                if (workTimeMedico.getStart().isBefore(workTime.getStart())){
                    if (workTimeMedico.getEnd().isAfter(workTime.getEnd())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean disponivel (WorkTime workTime){
        for(Consulta consultaMedico:this.getConsultas()){
            if(consultaMedico.getHorario().getDay().equals(workTime.getDay())){
                if (consultaMedico.getHorario().getStart().equals(workTime.getStart())) {
                    return false;
                }
                if (consultaMedico.getHorario().getStart().withMinute(30).isAfter(workTime.getStart())){
                    return false;
                }
                if(workTime.getStart().withMinute(30).isAfter(consultaMedico.getHorario().getStart())){
                    return false;
                }
            }
        }
        return true;
    }

}
