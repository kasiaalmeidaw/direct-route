package goeuro.task;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class DirectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private DirectConnectionServiceImpl connectionService;
	
	@Mock
	private LoadRoutsCommandLineRunner commandLineRunner;

	@InjectMocks
	@Autowired
	private DirectController directController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testController() throws Exception {
		when(connectionService.existDirectConnection(any(int.class), any(int.class))).thenReturn(true);
		this.mockMvc.perform(get("/api/direct").param("dep_sid", "1").param("arr_sid", "4")).andDo(print())
				.andExpect(status().is(HttpStatus.OK.value()));

	}

}
