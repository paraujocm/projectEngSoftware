package engsoftware.project.models;

import lombok.*;

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
    public void addConsutaToMedico(Consulta consulta){
        consultas.add(consulta);
        consulta.setMedico(this);
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

    public boolean isAvalible (WorkTime workTime){
        for(WorkTime workTime1:this.getWorkTimes()){
            if(workTime1.getDay().equals(workTime.getDay())){

            }
        }
    }
}
