package engsoftware.project.models;


import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PacienteTest {
    @Before
    public void setUp() {

    }

    @Test
    public void testAddConsultaToPaciente() {

        Paciente paciente = new Paciente();

        Consulta consulta1 = new Consulta();
        consulta1.setHorario(LocalDateTime.of(2019, 1, 21, 10, 0));

        Consulta consulta2 = new Consulta();
        consulta2.setHorario(LocalDateTime.of(2019, 1, 21, 10, 5));

        assertEquals(0, paciente.getConsultas().size());
        paciente.addConsutaToPaciente(consulta1);
        assertEquals(1, paciente.getConsultas().size());
        paciente.setProblematico(true);
        paciente.addConsutaToPaciente(consulta2);
        assertEquals(1, paciente.getConsultas().size());
    }
}

