import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Programs Application
 *
 * This application can be used to read and modify a csv file that contains a list of programs. The application was
 * written for a file with three columns/values representing the title, priority and status of the program respectively.
 * The application has 3 main functions:
 *
 * 1. Add a program to a provided csv file
 * 2. Pull a list of programs matching a specific priority and status from a provided csv file
 * 3. Delete a program from a provided csv file
 *
 * @author  Charles Chukwukaeme
 * @version 1.0
 * @since   2020-09-14
 */
public class Program {
    private static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    private static File workingFile;

    /**
     * Created this constructor to be able to run tests
     *
     * @param fileName - path or name of the file to use for the tests
     */
    public Program(String fileName) {
        workingFile = new File(fileName);
    }

    /**
     * This is the method used to run the application
     * Important Notes:
     * 1. It is written to work on one file per session
     * 2. Input/Output interaction with the user is through the console. Please follow the console output instructions
     * 3. It only works on existing files
     * 4. Once a file is located, the user can add, pull or delete records continuously
     * 5. When prompted, enter 'exit' to stop the application
     */
    public static void main(String[] args) {

        String line, title, priority, status;
        ArrayList<String> pullResult;

        try {
            System.out.println("****************Welcome to the SAIT Programs Application. Please enter the name or " +
                    "path of the file you want to work with....");

            if(getFile()) {
                while(true) {
                    System.out.println("What would you like to do with this file? Enter a command from the list of " +
                            "options: [add\tpull\tdelete\texit]");
                    line = stdIn.readLine();

                    if(line.equalsIgnoreCase("pull")) {
                        priority = getPriority();
                        status = getStatus();

                        pullResult = pullPrograms(priority, status);

                        if(pullResult.size() == 0){
                            System.out.println("No records match the pull criteria");
                        }
                        else {
                            System.out.println("The result of the pull: " + pullResult);
                        }
                    }

                    else if(line.equalsIgnoreCase("add")){

                        title = getTitle();
                        priority = getPriority();
                        status = getStatus();

                        addProgram(title, priority, status);
                    }

                    else if(line.equalsIgnoreCase("delete")){
                        title = getTitle();

                        deleteProgram(title);
                    }

                    else if(line.equalsIgnoreCase("exit")){
                        System.out.println("--------------------------------Goodbye----------------------------------");
                        break;
                    }

                    else{
                        System.out.print("Incorrect entry..... ");
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*-------------------------------------Coding Task Functions------------------------------------------------------*/

    /**
     * This method is used to pull a list of programs matching a specific priority and status from the working file
     *
     * @param priority - the priority attribute for the program to pull
     * @param status - the status attribute for the program to pull
     * @return - Returns a list of programs matching the provided parameters
     * @throws IOException - if the file exists but is a directory rather than a regular file, does not exist but cannot
     * be created, or cannot be opened for any other reason. Also throws IOException if an I/O error occurs while using
     * the BufferedWriter methods
     * @see IOException
     */
    public static ArrayList<String> pullPrograms(String priority, String status) throws IOException {

        ArrayList<String> result = new ArrayList<>();
        String row;
        String[] data;
        int dataSize, priorityIndex, statusIndex, titleLastIndex;

        FileReader fileReader = new FileReader(workingFile);
        BufferedReader csvReader = new BufferedReader(fileReader);

        while((row = csvReader.readLine()) != null) {
            data = row.split(",");

            dataSize = data.length;
            priorityIndex = dataSize - 2;
            statusIndex = dataSize - 1;
            titleLastIndex = dataSize - 3;

            if(data[priorityIndex].equalsIgnoreCase(priority) && data[statusIndex].equalsIgnoreCase(status)) {
                StringBuilder title = new StringBuilder(data[0]);
                for(int i = 1; i <= titleLastIndex; i++){
                    title.append(",").append(data[i]);
                }
                result.add(title.toString().trim());
            }
        }

        csvReader.close();

        return result;
    }

    /**
     * This method is used to add a new program to the working file
     *
     * @param title - The title of the program to add
     * @param priority - The priority of the program to add
     * @param status - The status of the program to add
     * @throws IOException - if the file exists but is a directory rather than a regular file, does not exist but cannot
     * be created, or cannot be opened for any other reason. Also throws IOException if an I/O error occurs while using
     * the BufferedWriter methods
     * @see IOException
     */
    public static void addProgram(String title, String priority, String status) throws IOException {

        String[] inputData = {title, priority, status};

        String csvRow = creatCSVRow(inputData);

        FileWriter fileWriter = new FileWriter(workingFile, true);
        BufferedWriter csvWriter = new BufferedWriter(fileWriter);
        csvWriter.append(csvRow);
        csvWriter.append("\n");

        csvWriter.flush();
        csvWriter.close();

        System.out.println("Program ["+ csvRow + "] added successfully");
    }

    /**
     * This method is used to delete a program from the working file
     *
     * @param title - the title of the program to delete
     * @throws IOException - if the file exists but is a directory rather than a regular file, does not exist but cannot
     * be created, or cannot be opened for any other reason. Also throws IOException if an I/O error occurs while using
     * the BufferedWriter or BufferedWriter methods
     * @see IOException
     */
    public static void deleteProgram(String title) throws IOException {

        String row = "";
        String[] data;

        //Temporary File to write to
        File temporaryFile = new File("temporaryFile.csv");
        FileWriter fileWriter = new FileWriter(temporaryFile, true);
        BufferedWriter csvWriter = new BufferedWriter(fileWriter);

        //Readers for the original file
        FileReader fileReader = new FileReader(workingFile);
        BufferedReader csvReader = new BufferedReader(fileReader);

        while((row = csvReader.readLine()) != null) {
            if(row.contains(title)){
                continue;
            }
            csvWriter.append(row);
            csvWriter.append("\n");
        }

        csvReader.close();
        csvWriter.flush();
        csvWriter.close();

        workingFile.delete();
        temporaryFile.renameTo(workingFile);

        System.out.println(title + " was successfully deleted");
    }
    /*----------------------------------------------------------------------------------------------------------------*/



    /*-------------------------------------Helper Functions-----------------------------------------------------------*/

    /**
     * Helper function used to return an existing csv file. User also has option to exit the program if a file can not
     * be located
     *
     * @return - 'true' if file found, 'false' is user wants to exit the program
     * @throws IOException - if an I/O error occurs with the system input
     * @see IOException
     */
    public static boolean getFile() throws IOException {
        String fileName;

        while(true) {
            fileName = stdIn.readLine();

            if(fileName.equalsIgnoreCase("exit")) {
                return false;
            }

            workingFile = new File(fileName);

            if(workingFile.exists()){
                return true;
            }

            System.out.println("This file does not exist. Please enter an accurate file name, file path or 'exit' to quit the program");
        }
    }

    /**
     * Helper function used to validate the title of a program entered by the user.
     * Currently limited to validating null inputs
     *
     * @return - a valid string based on validation parameters
     * @throws IOException - if an I/O error occurs with the system input
     * @see IOException
     */
    private static String getTitle() throws IOException {
        String title = "";

        System.out.println("Enter the program Title");
        while(true) {

            title = stdIn.readLine();

            if(!title.equals("")){
                break;
            }
            System.out.println("Please enter a title");
        }

        return title;
    }

    /**
     * Helper function used to validate the priority of a program entered by the user. Valid entries are case-insensitive
     * 'high', 'medium' and 'low'.
     *
     * @return a valid string based on validation parameters
     * @throws IOException - if an I/O error occurs with the system input
     * @see IOException
     */
    private static String getPriority() throws IOException {
        String priority = "";

        System.out.println("Enter priority. Valid options are 'High', 'Medium', 'Low'");
        while(true) {

            priority = stdIn.readLine();

            if(priority.equalsIgnoreCase("High") || priority.equalsIgnoreCase("Medium") || priority.equalsIgnoreCase("Low")){
                break;
            }
            System.out.println("You entered an invalid option. Valid options are 'High', 'Medium' or 'Low'");
        }

        return getPriorityCode(priority);
    }

    /**
     * Helper function used to return the appropriate priority attribute that goes into the csv file based. For example,
     * a priority of 'high' returns a code of '1'
     *
     * @param priority - the priority to convert
     * @return - '1', '2', or '3' based on the parameter
     */
    private static String getPriorityCode(String priority) {
        String result = "";

        if(priority.equalsIgnoreCase("High")){
            result = "1";
        }
        else if(priority.equalsIgnoreCase("Medium")){
            result = "2";
        }
        else if(priority.equalsIgnoreCase("Low")){
            result = "3";
        }

        return result;
    }

    /**
     * Helper function used to validate the status entered by the user
     *
     * @return - 'available' or 'not available' (case-insensitive)
     * @throws IOException if an I/O error occurs with the system input
     * @see IOException
     */
    private static String getStatus() throws IOException {
        String status = "";

        System.out.println("Enter status. Valid options are 'Available' or 'Not Available'");

        while(true) {
            status = stdIn.readLine();

            if(status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Not Available")){
                break;
            }
            System.out.println("You entered an invalid option. Valid options are 'Available' or 'Not Available'");
        }

        return status;
    }

    /**
     * Helper function used to build a csv row
     * Adapted from https://www.baeldung.com/java-csv on September 14, 2020
     *
     * @param data - an array of values to write to csv
     * @return - a string of the input array in the correct format
     */
    private static String creatCSVRow(String[] data){
        String csvRow = Stream.of(data).map(Program::escapeSpecialCharacters).collect(Collectors.joining(","));

        return csvRow;
    }

    /**
     * Helper function used to handle special characters that can impact writing into a csv file
     * Copied from https://www.baeldung.com/java-csv on September 14, 2020
     *
     * @param data - input values used to build a csv row
     * @return - modified value with special characters escaped
     */
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");

        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
    /*----------------------------------------------------------------------------------------------------------------*/
}
