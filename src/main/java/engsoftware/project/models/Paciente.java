package engsoftware.project.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente extends BaseModel  {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Consulta> consultas=new HashSet<>();

    private String nome;
    private int idade;
    private String nif;
    private boolean problematico;

    @Column(unique = true)
    private String nrUtenteSaude;

    public Paciente(String nome, int idade, String nif, boolean problematico, String nr_utente_saude) {
        this.nome=nome;
        this.idade=idade;
        this.nif=nif;
        this.problematico=problematico;
        this.nrUtenteSaude=nr_utente_saude;
    }

    // add consulta a um paciente
    public void addConsutaToPaciente(Consulta consulta){
        consultas.add(consulta);
        consulta.setPaciente(this);
    }

    public void removeConsultaFromPaciente(Consulta consulta){
        consultas.remove(consulta);
    }

    public void addPaciente(Paciente paciente){
        paciente.addPaciente(paciente);
    }

    public void removePaciente(Paciente paciente){
        paciente.removePaciente(paciente);
    }

}
