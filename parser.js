let http = new XMLHttpRequest(); //STEP 1 - Create a new xmlhttp-request object

http.open('get', 'Data/main.json', true); //STEP 2 - Prepare the request with the open() method

http.send(); //STEP 3 - execute the request with the send() function. We don't use any parameters with the send() function because this is a simple get request.

//STEP 4 - The XMLHttpRequest object has a property called onload. The onload property is fired when the transaction with the server is completed. We set the onload property equal to a function, so when the onload property is fired the function is executed.
http.onload = function() {
    if (this.readyState == 4 && this.status == 200) {
        let packages = JSON.parse(this.responseText);
        let output = "";

        for (let package of packages) {
            // console.log(package);


            //Multiple tutorial links
            //In other words if value is an array, we should display the submenu else no submenu
            let tutorial_li = ``;

            if (Array.isArray(package.tutorial)) {

                let listItems = ``;

                let count = 1;
                for (let option of package.tutorial) {
                    listItems += `<li><a href="${option}">Tutorial ${count} </a></li>`;
                    count ++;
                }

                tutorial_li = `
                <li class="multiple-option-tag">
                    <a>
                        <img src="Assets/Icons/tutorial_icon.svg">
                        Tutorial
                    </a>`
                + 
                    `<ul>`
                +
                    listItems
                +
                    `</ul>
                </li>`;
            } else {
                tutorial_li = `
                <li class="single-option-tag">
                    <a href="${package.tutorial}">
                        <img src="Assets/Icons/tutorial_icon.svg">
                        Tutorial
                    </a>
                </li>
                `;
            }

            //Complete HTML Output
            output += `
            <li>
            <div class="package-cell">

                    <div class="content">
                        <img src="${package.image}" alt="Java Package Logo" class="card-img">

                        <div id="text-section">
                            <h1 id="title">${package.name}</h1>

                            <p id="subtitle">Ecosystem</p>

                            <p id="description-text">${package.description}</p>
                        </div>

                    </div>
        
                    <div class="tags-section">

                        <ul class="tag-list">
                            <li class="single-option-tag">
                                <a href="${package.citation}">
                                    <img src="Assets/Icons/scientific_journal_icon.svg">
                                    Article
                                </a>
                            </li>

                            <li class="single-option-tag">
                                <a href="${package.github}">
                                    <img src="Assets/Icons/github_icon.svg">
                                    GitHub
                                </a>
                            </li>

                            <li class="single-option-tag">
                                <a href="${package.documentation}">
                                    <img src="Assets/Icons/documentation_icon.svg">
                                    Documentation
                                </a>
                            </li>

                            <li class="single-option-tag">
                                <a href="${package.website}">
                                    <img src="Assets/Icons/website_icon.svg">
                                    Website
                                </a>
                            </li>
                            `
                            +
                            tutorial_li
                            +
                            `
                            <li class="single-option-tag">
                                <a href="${package.fiji_imagej}">
                                    <img src="Assets/Icons/fiji_icon.svg">
                                    FIJI
                                </a>
                            </li>
                        </ul>
        
                    </div>
        
            </div>
            </li>
            `;
        }

        var json_to_html_tag = document.getElementById("packages-ul");
        json_to_html_tag.innerHTML = output;
    }
}


































// function printJSON(file_path) {
//     let output = "";

//     // const folderPath = './json-folder/';
    
//     const folderPath = './Data/';

//     // retrieve the list of files in the folder using the fetch API
//     fetch(folderPath)
//     .then(response => response.text())
//     .then(data => {

//         // parse the HTML response to extract the filenames
//         const parser = new DOMParser();
//         const htmlDoc = parser.parseFromString(data, 'text/html');
//         const links = htmlDoc.querySelectorAll('a');

//         links.forEach(link => {

//             const file = link.textContent;

//             if (file.includes('.json')) {
//                 const fileName = file.split('.')[0] + '.json';

//                 const filePath = folderPath + fileName;

//                 // console.log(filePath);

//                 fetch(filePath)
//                     .then(response => response.json())
//                     .then(jsonData => {

//                     jsonData.forEach(pack => {

//                         output += `
//                         <li>
//                         <div class="package-cell">

//                             <div class="main-cell">

//                                 <div class="content">
//                                     <img src="${pack.image}" alt="Java package Logo" class="card-img">

//                                     <div id="text-section">
//                                         <h1 id="title">${pack.name}</h1>

//                                         <p id="subtitle">${pack.category}</p>

//                                         <p id="description-text">${pack.description}</p>
//                                     </div>

//                                 </div>
                    
//                                 <div class="tags-section">

//                                     <ul>
//                                         <li>
//                                             <a href="${pack.citation}">
//                                                 <img src="Assets/Icons/scientific_journal_icon.svg">
//                                                 Article
//                                             </a>
//                                         </li>

//                                         <li>
//                                             <a href="${pack.github}">
//                                                 <img src="Assets/Icons/github_icon.svg">
//                                                 GitHub
//                                             </a>
//                                         </li>

//                                         <li>
//                                             <a href="${pack.documentation}">
//                                                 <img src="Assets/Icons/documentation_icon.svg">
//                                                 Documentation
//                                             </a>
//                                         </li>

//                                         <li>
//                                             <a href="${pack.website}">
//                                                 <img src="Assets/Icons/website_icon.svg">
//                                                 Website
//                                             </a>
//                                         </li>

//                                         <li>
//                                             <a href="${pack.tutorial}">
//                                                 <img src="Assets/Icons/tutorial_icon.svg">
//                                                 Tutorial
//                                             </a>
//                                         </li>

//                                         <li>
//                                             <a href="${pack.fiji_imagej}">
//                                                 <img src="Assets/Icons/fiji_icon.svg">
//                                                 FIJI
//                                             </a>
//                                         </li>
//                                     </ul>
                    
//                                 </div>
                    
//                             </div>

//                         </div>
//                         </li>
//                         `;
//                     })
//                     // return output;

//                     var json_to_html_tag = document.getElementById("packages-ul");
//                     json_to_html_tag.innerHTML = output;

//                     })
//                     .catch(error => console.error(error));
//               }
//         })
//     });
// }








// function test() {
//     fetch("./Data/main.json")
//         .then(response => response.json())
//         .then(jsonData => {

//         let output = '';

//         jsonData.forEach(pack => {

//             output += `
//             <li>
//             <div class="package-cell">

//                 <div class="main-cell">

//                     <div class="content">
//                         <img src="${pack.image}" alt="Java package Logo" class="card-img">

//                         <div id="text-section">
//                             <h1 id="title">${pack.name}</h1>

//                             <p id="subtitle">${pack.category}</p>

//                             <p id="description-text">${pack.description}</p>
//                         </div>

//                     </div>

//                     <div class="tags-section">

//                         <ul>
//                             <li>
//                                 <a href="${pack.citation}">
//                                     <img src="Assets/Icons/scientific_journal_icon.svg">
//                                     Article
//                                 </a>
//                             </li>

//                             <li>
//                                 <a href="${pack.github}">
//                                     <img src="Assets/Icons/github_icon.svg">
//                                     GitHub
//                                 </a>
//                             </li>

//                             <li>
//                                 <a href="${pack.documentation}">
//                                     <img src="Assets/Icons/documentation_icon.svg">
//                                     Documentation
//                                 </a>
//                             </li>

//                             <li>
//                                 <a href="${pack.website}">
//                                     <img src="Assets/Icons/website_icon.svg">
//                                     Website
//                                 </a>
//                             </li>

//                             <li>
//                                 <a href="${pack.tutorial}">
//                                     <img src="Assets/Icons/tutorial_icon.svg">
//                                     Tutorial
//                                 </a>
//                             </li>

//                             <li>
//                                 <a href="${pack.fiji_imagej}">
//                                     <img src="Assets/Icons/fiji_icon.svg">
//                                     FIJI
//                                 </a>
//                             </li>
//                         </ul>

//                     </div>

//                 </div>

//             </div>
//             </li>
//             `;

//         })

//         var json_to_html_tag = document.getElementById("packages-ul");
//         json_to_html_tag.innerHTML = output;

//         })
//         .catch(error => console.error(error));
// }






// // function parseJSONFile(file_path) {
    
// //     // var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
// //     // var XMLHttpRequest = require('xhr2');
// //     // global.XMLHttpRequest = require('xhr2');

// //     let http = new XMLHttpRequest(); //STEP 1 - Create a new xmlhttp-request object

// //     http.open('get', file_path, true)
// //     // http.open('get', 'Data/packs.json', true); //STEP 2 - Prepare the request with the open() method
    
// //     http.send(); //STEP 3 - execute the request with the send() function. We don't use any parameters with the send() function because this is a simple get request.

// //     //STEP 4 - The XMLHttpRequest object has a property called onload. The onload property is fired when the transaction with the server is completed. We set the onload property equal to a function, so when the onload property is fired the function is executed.
// //     http.onload = function() {
 
// //         if (this.readyState == 4 && this.status == 200) {
// //             let packs = JSON.parse(this.responseText);
// //             let output = "";
    
// //             for (let pack of packs) {
// //                 // console.log(pack);
// //                 output += `
// //                 <li>
// //                 <div class="package-cell">
    
// //                     <div class="main-cell">
    
// //                         <div class="content">
// //                             <img src="${pack.image}" alt="Java pack Logo" class="card-img">
    
// //                             <div id="text-section">
// //                                 <h1 id="title">${pack.name}</h1>
    
// //                                 <p id="subtitle">${pack.category}</p>
    
// //                                 <p id="description-text">${pack.description}</p>
// //                             </div>
    
// //                         </div>
            
// //                         <div class="tags-section">
    
// //                             <ul>
// //                                 <li>
// //                                     <a href="${pack.citation}">
// //                                         <img src="Assets/Icons/scientific_journal_icon.svg">
// //                                         Article
// //                                     </a>
// //                                 </li>
    
// //                                 <li>
// //                                     <a href="${pack.github}">
// //                                         <img src="Assets/Icons/github_icon.svg">
// //                                         GitHub
// //                                     </a>
// //                                 </li>
    
// //                                 <li>
// //                                     <a href="${pack.documentation}">
// //                                         <img src="Assets/Icons/documentation_icon.svg">
// //                                         Documentation
// //                                     </a>
// //                                 </li>
    
// //                                 <li>
// //                                     <a href="${pack.website}">
// //                                         <img src="Assets/Icons/website_icon.svg">
// //                                         Website
// //                                     </a>
// //                                 </li>
    
// //                                 <li>
// //                                     <a href="${pack.tutorial}">
// //                                         <img src="Assets/Icons/tutorial_icon.svg">
// //                                         Tutorial
// //                                     </a>
// //                                 </li>
    
// //                                 <li>
// //                                     <a href="${pack.fiji_imagej}">
// //                                         <img src="Assets/Icons/fiji_icon.svg">
// //                                         FIJI
// //                                     </a>
// //                                 </li>
// //                             </ul>
            
// //                         </div>
            
// //                     </div>
    
// //                 </div>
// //                 </li>
// //                 `;
// //             }
    
// //             var json_to_html_tag = document.getElementById("packages-ul");
// //             json_to_html_tag.innerHTML = output;

// //             // console.log(output);
// //         }
// //     }
// // }







// function fetchJSONFiles(folderPath) {
//     return fetch(folderPath)
//         .then(response => response.text())
//         .then(data => {
//             const parser = new DOMParser();
//             const htmlDoc = parser.parseFromString(data, 'text/html');
//             const links = htmlDoc.querySelectorAll('a');
//             const jsonFiles = [];

//             links.forEach(link => {
//                 const file = link.textContent;

//                 if (file.includes('.json')) {
//                     const fileName = file.split('.')[0] + '.json';
//                     const filePath = folderPath + fileName;
//                     jsonFiles.push(fetch(filePath).then(response => response.json()));
//                 }
//             });

//             return Promise.all(jsonFiles);
//         });
// }

// function displayPackages(jsonData) {
//     let output = "";

//     jsonData.forEach(json => {
//         json.forEach(pack => {
//             output += `
//             <li>
//                 <div class="package-cell">
//                     <div class="main-cell">
//                         <div class="content">
//                             <img src="${pack.image}" alt="Java package Logo" class="card-img">
//                             <div id="text-section">
//                                 <h1 id="title">${pack.name}</h1>
//                                 <p id="subtitle">${pack.category}</p>
//                                 <p id="description-text">${pack.description}</p>
//                             </div>
//                         </div>
//                         <div class="tags-section">
//                             <ul>
//                                 <li>
//                                     <a href="${pack.citation}">
//                                         <img src="Assets/Icons/scientific_journal_icon.svg">
//                                         Article
//                                     </a>
//                                 </li>
//                                 <li>
//                                     <a href="${pack.github}">
//                                         <img src="Assets/Icons/github_icon.svg">
//                                         GitHub
//                                     </a>
//                                 </li>
//                                 <li>
//                                     <a href="${pack.documentation}">
//                                         <img src="Assets/Icons/documentation_icon.svg">
//                                         Documentation
//                                     </a>
//                                 </li>
//                                 <li>
//                                     <a href="${pack.website}">
//                                         <img src="Assets/Icons/website_icon.svg">
//                                         Website
//                                     </a>
//                                 </li>
//                                 <li>
//                                     <a href="${pack.tutorial}">
//                                         <img src="Assets/Icons/tutorial_icon.svg">
//                                         Tutorial
//                                     </a>
//                                 </li>
//                                 <li>
//                                     <a href="${pack.fiji_imagej}">
//                                         <img src="Assets/Icons/fiji_icon.svg">
//                                         FIJI
//                                     </a>
//                                 </li>
//                             </ul>
//                         </div>
//                     </div>
//                 </div>
//             </li>`;
//         });
//     });

//     var json_to_html_tag = document.getElementById("packages-ul");
//     json_to_html_tag.innerHTML = output;
// }

