package engsoftware.project.services.filters.Paciente;

import lombok.Data;

@Data
public class FilterObjectPaciente {
    private String nome;
    private String nr_utente_saude;
    private boolean problematico;
}
