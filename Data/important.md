# Instructions for Updating the JSON Data File
**Note: main.json should never be edited manually! Follow the instructions below to update the data file used by the parser.js to display the packages.**

If you want to add new data to be displayed in packages, follow these steps:

1. Add your JSON file to the 'Data' directory.
2. Add your image to ./Assets/Images.
3. Run the main program in GenerateJSON.java.
4. Voila! The data file will now be updated.

Make sure that the JSON file you add follows the correct format to be included in the data file. Once you have added your JSON file, the main program will automatically update the data file with the new data and regenerate this .md-file in case it was accidentally deleted.

**About**

- Merges all JSON-files to one big file (main.json).
- Checks if the corresponding image exists in the ./Assets/Images/ directory.
- Reformats the value of the 'image'-key, in case full path is not specified, rather only the image name.
- In case the image is not found, the 'image'-key will hold the value '###### DID NOT FIND CORRESPONDING IMAGE IN ./Assets/Images ######'. Put in the image in the folder and update JSON and re-run GenerateJSON.java.

**Other things to note:**

- Never edit 'main.json' directly! Always use the provided process to update the main data file.
- Never add other files than .json. It should represent the data displayed by the parser (see JSON-object format below).
- Make sure to push your changes to the remote Git repository in order for the changes to be displayed on the production site.

**Format of JSON-object**

- JSON-objects must be in an array []
- Multiple JSON-object are separated by commas
- Each JSON-object in the file must have the following format

```
{
	"name": "Bigdataprocessor1",
	"image": "Assets/Images/bigdataprocessor_logo.svg",
	"category": "Fiji-plugin for Big Data Processing",
	"documentation": ["link to documents", "https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md"],
	"citation": "https://doi.org/10.1093/bioinformatics/btab106",
	"github": "https://github.com/bigdataprocessor/bigdataprocessor2", 
	"fiji_imagej": "https://imagej.net/plugins/big-data-processor-2",
	"website": "https://forum.image.sc/t/bigdataprocessor/34963/9",
	"tutorial": "https://en.wikipedia.org/wiki/Joshua_Tree_National_Park", 
	"documentation&tutorial": false,
	"description": "Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis..."
}
```

**Example of complete JSON-file**

```
[ 

	{
		"name": "Bigdataprocessor1",
		"image": "Assets/Images/bigdataprocessor_logo.svg",
		"category": "Fiji-plugin for Big Data Processing",
		"documentation": ["link to documents", "https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md"],
		"citation": "https://doi.org/10.1093/bioinformatics/btab106",
		"github": "https://github.com/bigdataprocessor/bigdataprocessor2", 
		"fiji_imagej": "https://imagej.net/plugins/big-data-processor-2",
		"website": "https://forum.image.sc/t/bigdataprocessor/34963/9",
		"tutorial": "https://en.wikipedia.org/wiki/Joshua_Tree_National_Park", 
		"documentation&tutorial": false,
		"description": "Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis..."
	},
	{
		"name": "Bigdataprocessor1",
		"image": "Assets/Images/bigdataprocessor_logo.svg",
		"category": "Fiji-plugin for Big Data Processing",
		"documentation": ["link to documents", "https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md"],
		"citation": "https://doi.org/10.1093/bioinformatics/btab106",
		"github": "https://github.com/bigdataprocessor/bigdataprocessor2", 
		"fiji_imagej": "https://imagej.net/plugins/big-data-processor-2",
		"website": "https://forum.image.sc/t/bigdataprocessor/34963/9",
		"tutorial": "https://en.wikipedia.org/wiki/Joshua_Tree_National_Park", 
		"documentation&tutorial": false,
		"description": "Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis..."
	}

] 
```