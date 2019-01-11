package engsoftware.project.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Especialidade extends BaseModel  {

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Medico medico;

    private String nome;
    private float preco;

    public Especialidade (String nome, float preco) {
        this.nome=nome;
        this.preco=preco;
    }
}
