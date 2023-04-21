# imglib2-jekyll


#Some Jekyll commands

Install ruby gems: `bundle install`

Run website (locally): `bundle exec jekyll serve`

#Troubleshooting: ERROR `/assets/css/style.css` not found?

Background: 

* The problem is that the linked path - to the css-file in all html-files under the folder "_includes" - is essentially not recognized/does not exist.


Solution: 

* Replace all occurencies of `/assets/css/style.css` with '/assets/main.css'

* If you are using VSCode easiest solution is to use the "search function" as in the picture below.

* Then press "replace all"

<img width="1440" alt="Screenshot 2023-04-21 at 20 14 32" src="https://user-images.githubusercontent.com/58792679/233706995-73dca615-417d-4b3d-bdcf-d8b9365f547a.png">
