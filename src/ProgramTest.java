import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void pullPrograms() {
    }

    @org.junit.jupiter.api.Test
    void addProgram() {
    }

    @org.junit.jupiter.api.Test
    void deleteProgram() {
    }

    @org.junit.jupiter.api.Test
    void getFile() {
        File testFileOne = new File("programs-test-file.csv");
        boolean testCaseOne = testFileOne.exists();
        assertEquals(false, testCaseOne);

        File testFileTwo = new File("programs_test_file.txt");
        boolean testCaseTwo = testFileTwo.exists();
        assertEquals(false, testCaseTwo);

        File testFileThree = new File("programs_test_file.xls");
        boolean testCaseThree = testFileThree.exists();
        assertEquals(false, testCaseThree);

        File testFileFour = new File("program_test_file.csv");
        boolean testCaseFour = testFileFour.exists();
        assertEquals(false, testCaseFour);

        File testFileFive = new File("programs_test_file.csv");
        boolean testCaseFive = testFileFive.exists();
        assertEquals(true, testCaseFive);
    }
}