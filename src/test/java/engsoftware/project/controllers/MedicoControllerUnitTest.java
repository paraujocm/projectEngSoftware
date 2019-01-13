package engsoftware.project.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class MedicoControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MedicoController medicoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(medicoController).build();
    }

    @Test
    public void testCreateClientSuccessfully() throws Exception {

        Especialidade dentista = new Especialidade("Dentista", 30);
        Medico medico = new Medico("medico1", "medico@gmail.com", "918765432", dentista);

        mockMvc.perform(post("/medico")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(medico)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("medico1"));
    }
}
