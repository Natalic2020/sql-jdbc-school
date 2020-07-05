package ua.com.foxminded.school;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public class ParsingFile {
	
	public void parseFile () throws Exception {
		String file = reseivePath("sql.script");
		CreatDB createDB = new CreatDB();
		
		try (Stream<String> fileInStream = Files.lines(Paths.get(file))) {
			 fileInStream.forEach(text -> {
					try {
						createDB.createTable(text);
					} catch (SQLException e) {
						e.printStackTrace();
					}		
			});	       
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void checkFile(String fileName) {
		Optional.ofNullable(fileName).orElseThrow(()-> new IllegalArgumentException("Null parameters are not allowed "));
		
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException("Empty parameters are not allowed " + fileName);
		}
	}

	private String reseivePath(String fileName) throws FileNotFoundException {
		checkFile(fileName);	
		ClassLoader classLoader = getClass().getClassLoader();
		Optional.ofNullable(classLoader.getResource(fileName)).orElseThrow(FileNotFoundException::new);

		File file = new File(classLoader.getResource(fileName).getFile());
	
		if (!file.isFile()) {
			throw new IllegalArgumentException("Directory is  not allowed  " + file.getAbsolutePath() + " Wait for a file ....");
		}
		return file.getAbsolutePath();
	}

}
