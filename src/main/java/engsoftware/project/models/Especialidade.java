package engsoftware.project.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Especialidade extends BaseModel  {

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @ToString.Exclude
    private Medico medico;

    private String nome;
    private float preco;

    public Especialidade (String nome, float preco) {
        this.nome=nome;
        this.preco=preco;
    }
}
