import java.util.*;
import java.io.*;

public class GenerateJSON {

    private String MAIN_JSON_NAME = "main.json";
    private String IMPORTANT_DIR_NAME = "important.md";

    public GenerateJSON() {

        String folderPath = "./Data/";

        String content = readFilesFromDir(folderPath);

        generateJSONFile(folderPath, content);

        generateAboutFile(folderPath);
    }


    private String readFilesFromDir(String folderPath) {

        File dir = new File(folderPath);

        String concatenatedJSONString = "[ \n\n";

        int count = 0;
        
        for (File f: dir.listFiles()) {

            if (f.isFile() && !f.getName().equals("main.json") && !f.getName().equals("important.md")) {
                String filePath = folderPath + f.getName();

                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    String jsonString = new String();

                    String line;

                    while ((line = br.readLine()) != null) { //CHECK FOR EMPTY ROWS

                        if (!line.trim().equals("")) {
                            jsonString += "\n" + line;
                        }
                    }

                    jsonString = jsonString.substring(2, jsonString.length()-1); //Removes '[' and ']'

                    if (count < dir.listFiles().length-1) {
                        jsonString += ",";
                    }

                    concatenatedJSONString += jsonString;
        
                } catch (IOException e) {
                    e.printStackTrace();
                }                
            }

            count ++;
        }

        concatenatedJSONString += "\n\n]";

        return concatenatedJSONString;
    }

    private void generateDirectory(String directoryPath) {
        // Create directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void generateFile(String directoryPath, String fileName, String fileContent) {
        String filePath = directoryPath + "/" + fileName;

        generateDirectory(directoryPath);

        // Create file and write content
        File file = new File(filePath);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(fileContent);
            writer.close();
            System.out.println("File created at " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateJSONFile(String directoryPath, String fileContent) {

        generateFile(directoryPath, MAIN_JSON_NAME, fileContent);
    }


    private void generateAboutFile(String directoryPath) {

        String fileContent = "# Instructions for Updating the JSON Data File\n"
                    + "**Note: main.json should never be edited manually! Follow the instructions below to update the data file used by the parser.js to display the packages.**\n\n"

                    + "If you want to add new data to be displayed in packages, follow these steps:\n\n"

                    + "1. Add your JSON file to the 'Data' directory.\n"
                    + "2. Run the main program in GenerateJSON.java.\n"
                    + "3. Voila! The data file will now be updated.\n\n"

                    + "Make sure that the JSON file you add follows the correct format to be included in the data file. Once you have added your JSON file, the main program will automatically update the data file with the new data and regenaret this .md-file in case it was accidentally deleted.\n\n"

                    + "**Other things to note:**\n"
                    + "- Never edit 'main.json' directly! Always use the provided process to update the main data file.\n"
                    + "- Make sure to push your changes to the remote Git repository in order for the changes to be displayed on the production site.\n\n"
                    + "**Format of JSON-object**\n"
                    + "Each JSON-object in the file must have the following format:\n\n"

                    + "```\n"

                    + "{\n"
                        + "\t\"name\": \"Bigdataprocessor1\",\n"
                        + "\t\"image\": \"Assets/Images/bigdataprocessor_logo.svg\",\n"
                        + "\t\"category\": \"Fiji-plugin for Big Data Processing\",\n"
                        + "\t\"documentation\": [\"link to documents\", \"https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md\"],\n"
                        + "\t\"citation\": \"https://doi.org/10.1093/bioinformatics/btab106\",\n"
                        + "\t\"github\": \"https://github.com/bigdataprocessor/bigdataprocessor2\", \n"
                        + "\t\"fiji_imagej\": \"https://imagej.net/plugins/big-data-processor-2\",\n"
                        + "\t\"website\": \"https://forum.image.sc/t/bigdataprocessor/34963/9\",\n"
                        + "\t\"tutorial\": \"https://en.wikipedia.org/wiki/Joshua_Tree_National_Park\", \n"
                        + "\t\"documentation&tutorial\": false,\n"
                        + "\t\"description\": \"Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis...\"\n"
                    + "}\n"
                    
                    +"```";
        

        generateFile(directoryPath, IMPORTANT_DIR_NAME, fileContent);
    }


    //TODO: Run this main-program after adding new .json-file(-s) to 'Data' directory!
    public static void main(String[] args) {
        new GenerateJSON();
    }



}