//Searches both the H1/title of the package and its description text
function searchPackages() {
    // get the search input value
    let searchValue = document.getElementById('search-bar').value.toLowerCase();

    // get all the h1 and p elements within the list items
    let packageTitles = document.querySelectorAll('#packages-ul li h1');
    let packageDescriptions = document.querySelectorAll('#packages-ul li p#description-text');

    // loop through the h1 and p elements and hide/show the list item based on the search value
    for (let i = 0; i < packageTitles.length; i++) {
        let listItem = packageTitles[i].parentElement.parentElement.parentElement.parentElement;
        let packageCard = packageTitles[i].parentElement.parentElement;

        if (packageTitles[i].innerHTML.toLowerCase().indexOf(searchValue) > -1 || packageDescriptions[i].innerHTML.toLowerCase().indexOf(searchValue) > -1) {
            // show the list item
            if (listItem.classList.contains('hidden')) {
                listItem.classList.remove('hidden');
                listItem.style.maxHeight = listItem.scrollHeight + "px";
                packageCard.classList.add('visible');

                setTimeout(function() { listItem.style.overflow = "visible"; }, 300);
            }
            
        } else {
            // hide the list item
            if (!listItem.classList.contains('hidden')) {
                listItem.style.overflow = "hidden";
                listItem.style.maxHeight = "0";
                packageCard.classList.remove('visible');

                setTimeout(function() { listItem.classList.add('hidden'); }, 300);
            }
        }
    }
}



  

  //Searches both the H1/title of the package and its description text but with bug, adding margi to the bottom
// function searchPackages() {
//     // get the search input value
//     let searchValue = document.getElementById('search-bar').value.toLowerCase();
  
//     // get all the h1 and p elements within the list items
//     let packageTitles = document.querySelectorAll('#packages-ul li h1');
//     let packageDescriptions = document.querySelectorAll('#packages-ul li p#description-text');
  
//     // loop through the h1 and p elements and hide/show the list item based on the search value
//     for (let i = 0; i < packageTitles.length; i++) {
//       let listItem = packageTitles[i].parentElement.parentElement.parentElement.parentElement;
//       let packageCard = packageTitles[i].parentElement.parentElement;
  
//       if (packageTitles[i].innerHTML.toLowerCase().indexOf(searchValue) > -1 ||
//           packageDescriptions[i].innerHTML.toLowerCase().indexOf(searchValue) > -1) {
//         listItem.classList.remove('hidden');
//         packageCard.classList.add('visible');
//       } else {
//         listItem.classList.add('hidden');
//         packageCard.classList.remove('visible');
//       }
//     }
//   }



// //Searches only the H1/title of the package
// function searchPackages() {
//     // get the search input value
//     let searchValue = document.getElementById('search-bar').value.toLowerCase();

//     // get all the h1 elements within the list items
//     let packageTitles = document.querySelectorAll('#packages-ul li h1');

//     // loop through the h1 elements and hide/show the list item based on the search value
//     for (let title of packageTitles) {
//         let listItem = title.parentElement.parentElement.parentElement.parentElement;
//         let packageCard = title.parentElement.parentElement;
        
//         if (title.innerHTML.toLowerCase().indexOf(searchValue) > -1) {
//             listItem.classList.remove('hidden');
//             packageCard.classList.add('visible');
//         } else {
//             listItem.classList.add('hidden');
//             packageCard.classList.remove('visible');
//         }
//     }
// }



//Without animation (remove animation part in css too)
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
  