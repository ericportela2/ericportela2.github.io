let http = new XMLHttpRequest(); //STEP 1 - Create a new xmlhttp-request object

http.open('get', 'Data/packages.json', true); //STEP 2 - Prepare the request with the open() method

http.send(); //STEP 3 - execute the request with the send() function. We don't use any parameters with the send() function because this is a simple get request.

//STEP 4 - The XMLHttpRequest object has a property called onload. The onload property is fired when the transaction with the server is completed. We set the onload property equal to a function, so when the onload property is fired the function is executed.
http.onload = function() {
    if (this.readyState == 4 && this.status == 200) {
        let packages = JSON.parse(this.responseText);
        let output = "";

        for (let package of packages) {
            // console.log(package);
            output += `
            <li>
            <div class="package-cell">

                <div class="main-cell">

                    <div class="content">
                        <img src="${package.image}" alt="Java Package Logo" class="card-img">

                        <div id="text-section">
                            <h1 id="title">${package.name}</h1>

                            <p id="subtitle">Ecosystem</p>

                            <p id="description-text">${package.description}</p>
                        </div>

                    </div>
        
                    <div class="tags-section">

                        <ul>
                            <li>
                                <a href="${package.citation}">
                                    <img src="Assets/Icons/scientific_journal_icon.svg">
                                    Article
                                </a>
                            </li>

                            <li>
                                <a href="${package.github}">
                                    <img src="Assets/Icons/github_icon.svg">
                                    GitHub
                                </a>
                            </li>

                            <li>
                                <a href="${package.documentation}">
                                    <img src="Assets/Icons/documentation_icon.svg">
                                    Documentation
                                </a>
                            </li>

                            <li>
                                <a href="${package.website}">
                                    <img src="Assets/Icons/website_icon.svg">
                                    Website
                                </a>
                            </li>

                            <li>
                                <a href="${package.tutorial}">
                                    <img src="Assets/Icons/tutorial_icon.svg">
                                    Tutorial
                                </a>
                            </li>

                            <li>
                                <a href="${package.fiji_imagej}">
                                    <img src="Assets/Icons/fiji_icon.svg">
                                    FIJI
                                </a>
                            </li>
                        </ul>
        
                    </div>
        
                </div>

            </div>
            </li>
            `;
        }

        var json_to_html_tag = document.getElementById("packages-ul");
        json_to_html_tag.innerHTML = output;
    }
}