package goeuro.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 
 * CommandLineRunner loads data from file to memory
 *
 */
@Component
@Profile("!test")
public class LoadRoutsCommandLineRunner implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(LoadRoutsCommandLineRunner.class);

	@Autowired
	public DirectConnectionService directoConnectionService;

	@Override
	public void run(String... strings) throws Exception {
		if (strings.length < 1) {
			throw new IllegalArgumentException(
					"Failed due to lack of argument. Please provide connections file path as a command line argument");
		}
		logger.info("Loading data...");
		String filePath = strings[0];
		FileInputStream inputStream = null;
		try {
			Scanner scanner = createFileScaner(inputStream, filePath);
			skipFirstLine(scanner);
			loadRoutsFile(scanner);
			handleScannerAfterRead(scanner);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private Scanner createFileScaner(FileInputStream inputStream, String filePath) throws IOException {
		inputStream = new FileInputStream(filePath);
		return new Scanner(inputStream, "UTF-8");
	}

	private void loadRoutsFile(Scanner scanner) {
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			line = skipRoutId(line);
			int[] routArray = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			directoConnectionService.getRoutsList().add(routArray);
		}
	}

	private void skipFirstLine(Scanner sc) {
		sc.nextLine();
	}

	private String skipRoutId(String line) {
		line.trim();
		line = line.substring(line.indexOf(" ") + 1);
		line.trim();
		return line;
	}

	private void handleScannerAfterRead(Scanner scanner) throws IOException {
		if (scanner.ioException() != null) {
			throw scanner.ioException();
		}
		if (scanner != null) {
			scanner.close();
		}
	}

	public void setDirectoConnectionService(DirectConnectionService directoConnectionService) {
		this.directoConnectionService = directoConnectionService;
	}
};