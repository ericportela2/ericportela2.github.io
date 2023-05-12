import java.io.*;

public class GenerateJSON {

    private String MAIN_JSON_NAME = "main.json";
    private String IMPORTANT_DIR_NAME = "important.md";
    private String IMAGES_FOLDER_DIR = "./Assets/Images/";

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

            if (f.isFile() && !f.getName().equals("main.json") && !f.getName().equals("important.md")) { //Ignores the 2 generated files
                String filePath = folderPath + f.getName();

                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    String jsonString = new String();

                    String line;

                    while ((line = br.readLine()) != null) { //CHECK FOR EMPTY ROWS

                        if (!line.trim().equals("")) {


                            if (line.contains("\"image\": ")) {
                                String imgName = line.replaceAll(" ", "").split(":")[1];
    
                                if (imgName.length() != 0 && !imgName.contains("Assets/Images/")) {
    
                                    imgName = imgName.replaceAll("\"",""); //!Remove quotation marks ""
                                    imgName = imgName.replaceAll(",", ""); //!Remove commas ,
                                    imgName = imgName.replaceAll("/", "");//!Remove slash /
                                    
                                    //!LOOK FOR IMAGE IN FOLDER!
                                    if(findImage(imgName)) { //FOUND
                                        String imgPath = IMAGES_FOLDER_DIR + imgName;

                                        jsonString += "\n\t\t\"image\": " + "\"" + imgPath + "\"" + ",";
                                    } else { //NOT FOUND
                                        System.out.println("DID NOT FIND"
                                                            + imgName + "\n"
                                                            + "in the Assets/Images directory."
                                                            );
    
                                        jsonString += "\n\t\t\"image\": " + "\"" + "###### DID NOT FIND CORRESPONDING IMAGE IN ./Assets/Images ######" + "\"" + ",";
                                    }
                                } else {
                                    jsonString += "\n" + line;
                                }
                            } else {
                                jsonString += "\n" + line;
                            }
                        }

                        // if (!line.trim().equals("")) {
                        //     if (line.contains("\"image\": ")) {
                        //         System.out.println(line.strip().trim());
                        //     } else {
                        //         jsonString += "\n" + line;

                        //     }
                        // }
                    }
                    
                    jsonString = jsonString.substring(2, jsonString.length()-2); //Removes original '[' and ']'

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

        concatenatedJSONString += "\n\n\n]";

        return concatenatedJSONString;
    }

    private boolean findImage(String imgName) {

        File dir = new File(IMAGES_FOLDER_DIR);
        
        for (File f: dir.listFiles()) {
            if (f.getName().equals(imgName)) {
                return true;
            }
        }

        return false;
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
                            + "2. Add your image to ./Assets/Images.\n"
                            + "3. Run the main program in GenerateJSON.java.\n"
                            + "4. Voila! The data file will now be updated.\n\n"

                            + "Make sure that the JSON file you add follows the correct format to be included in the data file. Once you have added your JSON file, the main program will automatically update the data file with the new data and regenerate this .md-file in case it was accidentally deleted.\n\n"

                            + "**About**\n\n"

                            + "- Merges all JSON-files to one big file (main.json).\n"
                            + "- Checks if the corresponding image exists in the ./Assets/Images/ directory.\n"
                            + "- Reformats the value of the 'image'-key, in case full path is not specified, rather only the image name.\n"
                            + "- In case the image is not found, the 'image'-key will hold the value '###### DID NOT FIND CORRESPONDING IMAGE IN ./Assets/Images ######'. Put in the image in the folder and update JSON and re-run GenerateJSON.java.\n\n"

                            + "**Other things to note:**\n\n"
                            + "- Never edit 'main.json' directly! Always use the provided process to update the main data file.\n"
                            + "- Never add other files than .json. It should represent the data displayed by the parser (see JSON-object format below).\n"
                            + "- Make sure to push your changes to the remote Git repository in order for the changes to be displayed on the production site.\n\n"
                            
                            + "**Format of JSON-object**\n\n"
                            + "- JSON-objects must be in an array []\n"
                            + "- Multiple JSON-object are separated by commas\n"
                            + "- Each JSON-object in the file must have the following format\n\n"

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
                            
                            +"```\n\n"

                            + "**Example of complete JSON-file**\n\n"

                            + "```\n"

                            + "[ \n\n"

                            + "\t{\n"
                                + "\t\t\"name\": \"Bigdataprocessor1\",\n"
                                + "\t\t\"image\": \"Assets/Images/bigdataprocessor_logo.svg\",\n"
                                + "\t\t\"category\": \"Fiji-plugin for Big Data Processing\",\n"
                                + "\t\t\"documentation\": [\"link to documents\", \"https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md\"],\n"
                                + "\t\t\"citation\": \"https://doi.org/10.1093/bioinformatics/btab106\",\n"
                                + "\t\t\"github\": \"https://github.com/bigdataprocessor/bigdataprocessor2\", \n"
                                + "\t\t\"fiji_imagej\": \"https://imagej.net/plugins/big-data-processor-2\",\n"
                                + "\t\t\"website\": \"https://forum.image.sc/t/bigdataprocessor/34963/9\",\n"
                                + "\t\t\"tutorial\": \"https://en.wikipedia.org/wiki/Joshua_Tree_National_Park\", \n"
                                + "\t\t\"documentation&tutorial\": false,\n"
                                + "\t\t\"description\": \"Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis...\"\n"
                            + "\t},\n"

                            + "\t{\n"
                                + "\t\t\"name\": \"Bigdataprocessor1\",\n"
                                + "\t\t\"image\": \"Assets/Images/bigdataprocessor_logo.svg\",\n"
                                + "\t\t\"category\": \"Fiji-plugin for Big Data Processing\",\n"
                                + "\t\t\"documentation\": [\"link to documents\", \"https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md\"],\n"
                                + "\t\t\"citation\": \"https://doi.org/10.1093/bioinformatics/btab106\",\n"
                                + "\t\t\"github\": \"https://github.com/bigdataprocessor/bigdataprocessor2\", \n"
                                + "\t\t\"fiji_imagej\": \"https://imagej.net/plugins/big-data-processor-2\",\n"
                                + "\t\t\"website\": \"https://forum.image.sc/t/bigdataprocessor/34963/9\",\n"
                                + "\t\t\"tutorial\": \"https://en.wikipedia.org/wiki/Joshua_Tree_National_Park\", \n"
                                + "\t\t\"documentation&tutorial\": false,\n"
                                + "\t\t\"description\": \"Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis...\"\n"
                            + "\t}\n\n"

                            + "] \n"
                            
                            +"```";

        
        generateFile(directoryPath, IMPORTANT_DIR_NAME, fileContent);
    }


    //TODO: Run this main-program after adding new .json-file(-s) to 'Data' directory!
    public static void main(String[] args) {
        new GenerateJSON();
    }



}