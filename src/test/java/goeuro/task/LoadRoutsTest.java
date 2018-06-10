package goeuro.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoadRoutsTest {

	@InjectMocks
	private LoadRoutsCommandLineRunner commandLineRunner;

	@InjectMocks
	private DirectConnectionServiceImpl connectionService;

	@Mock
	private FileInputStream fileInput;

	@Test
	public void fileLoadedTest() throws Exception {
		commandLineRunner.setDirectoConnectionService(connectionService);
		commandLineRunner.run(new String[] { "src/test/resources/routs.txt" });
	}

	@Test
	public void testDirectRouteFound() throws Exception {
		commandLineRunner.setDirectoConnectionService(connectionService);
		commandLineRunner.run(new String[] { "src/test/resources/routs.txt" });
			assertTrue(connectionService.existDirectConnection(1,6));
	}
	
	@Test
	public void testDirectRouteNotd() throws Exception {
		commandLineRunner.setDirectoConnectionService(connectionService);
		commandLineRunner.run(new String[] { "src/test/resources/routs.txt" });
			assertFalse(connectionService.existDirectConnection(1,10));
	}
}
