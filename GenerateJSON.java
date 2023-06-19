import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Ändra preprint till name_tag så att man kan själv bestämma namnet
//Lägg till json object i tutorial-listan m 2 nycklar, link + name_tag, återigen så att man kan ändra namnet på taggen
//Blogg-tag: https://doi.org/10.1093/bioinformatics/bts543

public class GenerateJSON {

    //Attributes/Constants
    private String MAIN_JSON_NAME = "main.json";
    private String IMPORTANT_DIR_NAME = "important.md";
    private String IMAGES_FOLDER_DIR = "./Assets/Images/";


     //TODO: Run this main-program after adding new .json-file(-s) to 'Data' directory + corresponfing images to 'Assets/Images' directory!
     public static void main(String[] args) {
        new GenerateJSON();
    }


    /**
     * This is a constructor for the GenerateJSON class.
     * It initializes the folderPath variable to "./Data/", reads the files from the directory specified 
     * by folderPath using the readFilesFromDir method, generates a JSON file using the generateJSONFile
     * method and generates an "about" file using the generateAboutFile method.
     */

    public GenerateJSON() {

        String folderPath = "./Data/";

        String content = readFilesFromDir(folderPath);

        generateJSONFile(folderPath, content);

        generateAboutFile(folderPath);
    }

    /**
     * Shuffles the provided array of files, removes files with names "main.json" or "important.md",
     * and returns the resulting list of files.
     *
     * @param fileArray the array of files to shuffle and filter
     * @return the shuffled and filtered list of files
     */

    private List<File> shuffleFiles(File[] fileArray) {

        //Change as per request, randomly iterating over dir
        List<File> files = new ArrayList<>(Arrays.asList(fileArray));
        Collections.shuffle(files); //Randomly permutes the specified list using a default source of randomness

        // Remove files with names "main.json" or "important.md"
        files.removeIf((file) -> file.getName().equals("main.json") || file.getName().equals("important.md"));

        return files;
    }


    /**
     * Reads all files (excluding "main.json" and "important.md") in a given directory and concatenates 
     * their contents into a JSON-formatted string, including the image paths of any images referenced 
     * in the files (if found in the "./Assets/Images" directory).
     * @param folderPath The file path of the directory containing the files to be read.
     * @return A string containing the concatenated JSON-formatted contents of the files (excluding 
     * "main.json" and "important.md"), including the image paths of any images referenced in the files
     * (if found in the "./Assets/Images" directory).
     */

    private String readFilesFromDir(String folderPath) {

        File dir = new File(folderPath);

        String concatenatedJSONString = "[ \n\n";

        int count = 0;

        List<File> files = shuffleFiles(dir.listFiles()); //Shuffle the files, randomly

        for (File f: files) { 
        // for (File f: dir.listFiles()) {

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
                    }
                    
                    jsonString = jsonString.substring(2, jsonString.length()-2); //Removes original '[' and ']'

                    if (count < files.size()-1) {
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


    /**
     * Looks for an image file with the specified name in the "Assets/Images" directory.
     * @param imgName the name of the image file to look for.
     * @return true if the image file is found, false otherwise.
     */

    private boolean findImage(String imgName) {

        File dir = new File(IMAGES_FOLDER_DIR);
        
        for (File f: dir.listFiles()) {
            if (f.getName().equals(imgName)) {
                return true;
            }
        }

        return false;
    }


     /**
      * Creates a new directory at the specified path if it doesn't already exist.
      * @param directoryPath the path of the directory to be created
      */

    private void generateDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }


    /**
     * This method generates a new file at the given directory path with the given file name and file content.
     * If the directory does not exist, it will be created.
     * @param directoryPath the path of the directory where the file will be created
     * @param fileName the name of the new file to be created
     * @param fileContent the content to be written into the new file
     */

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
            System.out.println("\n* File created at " + file.getAbsolutePath() + "\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Generates a JSON file with the given file content in the specified directory path.
     * @param directoryPath the path of the directory where the file will be created.
     * @param fileContent the content of the file to be created.
     */

    private void generateJSONFile(String directoryPath, String fileContent) {
        generateFile(directoryPath, MAIN_JSON_NAME, fileContent);
    }


    /**
     * Generates an about file with instructions on how to update the JSON Data File.
     * It concatenates all JSON files to one big file (main.json), checks if the corresponding image exists in the
     * Assets/Images directory and reformats the value of the 'image'-key, in case a full path is not specified.
     * If the image is not found, the 'image'-key will hold the value '###### DID NOT FIND CORRESPONDING IMAGE IN ./Assets/Images ######'.
     * The method ensures that the JSON file added follows the correct format to be included in the data file.
     * @param directoryPath a String representing the path to the directory where the JSON file is to be saved
     * @throws IOException if the input file is not found
     */

    private void generateAboutFile(String directoryPath) {

        String fileContent = "# Instructions for Updating the JSON Data File\n"
                            + "**Note: main.json should never be edited manually! Follow the instructions below to update the data file used by the parser.js to display the packages.**\n\n"

                            + "If you want to add new data to be displayed in packages, follow these steps:\n\n"

                            + "1. Add your JSON file to the 'Data' directory.\n"
                            + "2. Add your image to ./Assets/Images.\n"
                            + "3. Run the main program in GenerateJSON.java.\n"
                            + "4. Voila! The data file will now be updated.\n"
                            + "5. Push changes to GitHub in order to see changes/new data on production site.\n\n"

                            + "Make sure that the JSON file you add follows the correct format to be included in the data file (see exact format, below). Once you have added your JSON file, the main program will automatically update the data file with the new data and regenerate this .md-file in case it was accidentally deleted.\n\n"

                            + "**About**\n\n"

                            + "- Merges all JSON-files to one big file (main.json).\n"
                            + "- Checks if the corresponding image exists in the ./Assets/Images/ directory.\n"
                            + "- Reformats the value of the 'image'-key, in case full path is not specified (for instance if only the image name (excl. full path) is added).\n"
                            + "- In case the image is not found, the 'image'-key will hold the value '###### DID NOT FIND CORRESPONDING IMAGE IN ./Assets/Images ######'. Put the image in the folder, make sure value 'image'-key holds the correct name of the image and update JSON and re-run GenerateJSON.java.\n\n"

                            + "**Other things to note**\n\n"
                            + "- Never edit 'main.json' directly! Always use the provided process to update the main data file.\n"
                            + "- Never add other files than .json. It should represent the data displayed by the parser (see JSON-object format below).\n"
                            + "- Make sure to push your changes to the remote Git repository in order for the changes to be displayed on the production site.\n\n"
                            
                            + "**Format of JSON-object**\n\n"
                            + "- JSON-objects must be in an array []\n"
                            + "- Multiple JSON-objects are separated by commas\n"
                            + "- Each JSON-object in the file must have the following format\n"
                            + "- Note that 'image'-key can either be specified by the image-name or full path to the image. When 'GenerateJSON.java is run, the value will automatically be updated to the correct path.'\n"
                            + "- Note that 'tutorial'-key can either be one individual link (String) or an Array containing several links (Strings). In the latter case, a submenu will appear at hover over the tutorial tag, listing all links provided.'\n"
                            + "- Note that 'citation'-key holds an object which in turn contains two keys. 'link'-key and 'preprint'-key. The first is self-explanatory, but the latter one is of boolean type, specifying if citation is a preprint or an article. The value affects the name of the tag itself in the GUI.'\n\n"
                            + "- Note that 'blog'-key can either be null or a String containing the link to the blog."


                            + "```\n"

                            + "{\n"
                                + "\t\"name\": \"Bigdataprocessor1\",\n"
                                + "\t\"image\": \"Assets/Images/bigdataprocessor_logo.svg\",\n"
                                + "\t\"category\": \"Fiji-plugin for Big Data Processing\",\n"
                                + "\t\"documentation\": [\"link to documents\", \"https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md\"],\n"
                                + "\t\"citation\": {\"link\": \"https://doi.org/10.1093/bioinformatics/btab106\", \n" + "\t\t\t\t\"preprint\": false},\n"
                                + "\t\"github\": \"https://github.com/bigdataprocessor/bigdataprocessor2\", \n"
                                + "\t\"fiji_imagej\": \"https://imagej.net/plugins/big-data-processor-2\",\n"
                                + "\t\"website\": \"https://forum.image.sc/t/bigdataprocessor/34963/9\",\n"
                                + "\t\"tutorial\": \"https://en.wikipedia.org/wiki/Joshua_Tree_National_Park\", \n"
                                + "\t\"documentation&tutorial\": false,\n"
                                + "\t\"description\": \"Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis...\"\n"
                                + "\t\"blog\": \"null\"\n"
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
                                + "\t\t\"citation\": {\"link\": \"https://doi.org/10.1093/bioinformatics/btab106\", \n" + "\t\t\t\t\"preprint\": false},\n"
                                + "\t\t\"github\": \"https://github.com/bigdataprocessor/bigdataprocessor2\", \n"
                                + "\t\t\"fiji_imagej\": \"https://imagej.net/plugins/big-data-processor-2\",\n"
                                + "\t\t\"website\": \"https://forum.image.sc/t/bigdataprocessor/34963/9\",\n"
                                + "\t\t\"tutorial\": \"https://en.wikipedia.org/wiki/Joshua_Tree_National_Park\", \n"
                                + "\t\t\"documentation&tutorial\": false,\n"
                                + "\t\t\"description\": \"Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis...\"\n"
                                + "\t\t\"blog\": \"null\"\n"
                            + "\t},\n"

                            + "\t{\n"
                                + "\t\t\"name\": \"Bigdataprocessor1\",\n"
                                + "\t\t\"image\": \"Assets/Images/bigdataprocessor_logo.svg\",\n"
                                + "\t\t\"category\": \"Fiji-plugin for Big Data Processing\",\n"
                                + "\t\t\"documentation\": [\"link to documents\", \"https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md\"],\n"
                                + "\t\t\"citation\": {\"link\": \"https://doi.org/10.1093/bioinformatics/btab106\", \n" + "\t\t\t\t\"preprint\": false},\n"
                                + "\t\t\"github\": \"https://github.com/bigdataprocessor/bigdataprocessor2\", \n"
                                + "\t\t\"fiji_imagej\": \"https://imagej.net/plugins/big-data-processor-2\",\n"
                                + "\t\t\"website\": \"https://forum.image.sc/t/bigdataprocessor/34963/9\",\n"
                                + "\t\t\"tutorial\": \"https://en.wikipedia.org/wiki/Joshua_Tree_National_Park\", \n"
                                + "\t\t\"documentation&tutorial\": false,\n"
                                + "\t\t\"description\": \"Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis...\"\n"
                                + "\t\t\"blog\": \"null\"\n"

                            + "\t}\n\n"

                            + "] \n"
                            
                            +"```";

        
        generateFile(directoryPath, IMPORTANT_DIR_NAME, fileContent);
    }
}