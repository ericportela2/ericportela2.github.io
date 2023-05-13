function searchPackages() {
    // get the search input value
    let searchValue = document.getElementById('search-bar').value.toLowerCase();

    // get all the h1 elements within the list items
    let packageTitles = document.querySelectorAll('#packages-ul li h1');

    // loop through the h1 elements and hide/show the list item based on the search value
    for (let title of packageTitles) {
        let listItem = title.parentElement.parentElement.parentElement.parentElement;
        let packageCard = title.parentElement.parentElement;
        
        if (title.innerHTML.toLowerCase().indexOf(searchValue) > -1) {
            listItem.classList.remove('hidden');
            packageCard.classList.add('visible');
        } else {
            listItem.classList.add('hidden');
            packageCard.classList.remove('visible');
        }
    }
}

// function searchPackages() {
//     // get the search input value
//     let searchValue = document.getElementById('search-bar').value.toLowerCase();

//     // get all the h1 elements within the list items
//     let packageTitles = document.querySelectorAll('#packages-ul li h1');

//     // loop through the h1 elements and hide/show the list item based on the search value
//     for (let title of packageTitles) {
//         let listItem = title.parentElement.parentElement.parentElement.parentElement;
//         if (title.innerHTML.toLowerCase().indexOf(searchValue) > -1) {
//             listItem.style.display = "";
//         } else {
//             listItem.style.display = "none";
//         }
//     }
// }
  