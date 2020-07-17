package ua.com.foxminded.school;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.util.FileParser;

class FileParserTest {

    FileParser fileParser = new FileParser();

    @Test
    public void checkFileName_shouldThrowIllegalArgumentException_whenInputNull() {
        final String fileName = null;
        assertThrows(IllegalArgumentException.class, () -> fileParser.checkFileName(fileName));
    }

    @Test
    public void checkFileNamet_shouldThrowIllegalArgumentException_whenInputEmptyString() {
        final String fileName = "";
        assertThrows(IllegalArgumentException.class, () -> fileParser.checkFileName(fileName));
    }

    @Test
    public void readFileToLines_shouldReadFileToList_whenInputCorrect() {
        final String fileName = "sql_test.script";
        List<String> expected = new ArrayList<>();
        expected.add("create table school.groups (group_id serial PRIMARY KEY, group_name character(50)  NOT NULL);");
        List<String> actual = fileParser.readFileToLines(fileName);
        assertEquals(expected, actual);
    }

    @Test
    public void readFileToLines_shouldThrowIllegalArgumentException_whenInputFileIsNotValid() {
        final String fileName = "not_file.txt";
        assertThrows(IllegalArgumentException.class, () -> fileParser.readFileToLines(fileName));
    }
}
