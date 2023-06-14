# Instructions for Updating the JSON Data File
**Note: main.json should never be edited manually! Follow the instructions below to update the data file used by the parser.js to display the packages.**

If you want to add new data to be displayed in packages, follow these steps:

1. Add your JSON file to the 'Data' directory.
2. Add your image to ./Assets/Images.
3. Run the main program in GenerateJSON.java.
4. Voila! The data file will now be updated.
5. Push changes to GitHub in order to see changes/new data on production site.

Make sure that the JSON file you add follows the correct format to be included in the data file (see exact format, below). Once you have added your JSON file, the main program will automatically update the data file with the new data and regenerate this .md-file in case it was accidentally deleted.

**About**

- Merges all JSON-files to one big file (main.json).
- Checks if the corresponding image exists in the ./Assets/Images/ directory.
- Reformats the value of the 'image'-key, in case full path is not specified (for instance if only the image name (excl. full path) is added).
- In case the image is not found, the 'image'-key will hold the value '###### DID NOT FIND CORRESPONDING IMAGE IN ./Assets/Images ######'. Put the image in the folder, make sure value 'image'-key holds the correct name of the image and update JSON and re-run GenerateJSON.java.

**Other things to note**

- Never edit 'main.json' directly! Always use the provided process to update the main data file.
- Never add other files than .json. It should represent the data displayed by the parser (see JSON-object format below).
- Make sure to push your changes to the remote Git repository in order for the changes to be displayed on the production site.

**Format of JSON-object**

- JSON-objects must be in an array []
- Multiple JSON-objects are separated by commas
- Each JSON-object in the file must have the following format
- Note that 'image'-key can either be specified by the image-name or full path to the image. When 'GenerateJSON.java is run, the value will automatically be updated to the correct path.'
- Note that 'tutorial'-key can either be one individual link (String) or an Array containing several links (Strings). In the latter case, a submenu will appear at hover over the tutorial tag, listing all links provided.'
- Note that 'citation'-key holds an object which in turn contains two keys. 'link'-key and 'preprint'-key. The first is self-explanatory, but the latter one is of boolean type, specifying if citation is a preprint or an article. The value affects the name of the tag itself in the GUI.'

```
{
	"name": "Bigdataprocessor1",
	"image": "Assets/Images/bigdataprocessor_logo.svg",
	"category": "Fiji-plugin for Big Data Processing",
	"documentation": ["link to documents", "https://github.com/bigdataprocessor/bigdataprocessor2/blob/master/README.md"],
	"citation": {"link": "https://doi.org/10.1093/bioinformatics/btab106", 
				"preprint": false},
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
		"citation": {"link": "https://doi.org/10.1093/bioinformatics/btab106", 
					"preprint": false},
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
		"citation": {"link": "https://doi.org/10.1093/bioinformatics/btab106", 
					"preprint": false},
		"github": "https://github.com/bigdataprocessor/bigdataprocessor2", 
		"fiji_imagej": "https://imagej.net/plugins/big-data-processor-2",
		"website": "https://forum.image.sc/t/bigdataprocessor/34963/9",
		"tutorial": "https://en.wikipedia.org/wiki/Joshua_Tree_National_Park", 
		"documentation&tutorial": false,
		"description": "Suscipit inceptos est felis purus aenean aliquet adipiscing diam venenatis..."
	}

] 
```