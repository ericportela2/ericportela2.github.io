# imglib2-jekyll

* This is the repo for an updated version of the imglib2-blog site that will include a new "Packages section".
* This project is part of an internship for a research group at "Institutionen för Experimentell Medicinsk Vetenskap; avdelningen för Lungbiologi".
* Period: 2023 April - 2023 July

# Table of Contents
1. [Basic structure of Jekyll site](#basic-structure-of-jekyll-site)
2. [Docker commands](#some-docker-commands-primarily)
3. [Jekyll Commands](#some-jekyll-commands-secondarily)
4. [Troubleshooting](#troubleshooting)
   
   4.1 [CSS Not Found](#error-assetscssstylecss-not-found)

## Basic structure of Jekyll site

* See Jekyll's site: https://jekyllrb.com/docs/structure/

## Some Docker commands (primarily)

* NOTE: Make sure to have docker installed!

Build containers + run blog with live preview: `make server`

Rebuild containers without cache: `make build`

See development guide in repo from fastpages for more commands: https://github.com/fastai/fastpages/blob/master/_fastpages_docs/DEVELOPMENT.md

## Some Jekyll commands (secondarily)

* NOTE: Make sure to have ruby + bundler installed!

Install ruby gems: `bundle install`

Run website (locally): `bundle exec jekyll serve`



## Troubleshooting

### ERROR `/assets/css/style.css` not found

<img width="658" alt="Screenshot 2023-04-21 at 20 27 45" src="https://user-images.githubusercontent.com/58792679/233709542-d915090a-a593-4968-8ff5-cd7ef15aea4f.png">

#### Background: 

* The problem is that the linked path to the css-file (in all html-files under the folder "_includes") is essentially not recognized/does not exist.



#### Solution: 

* Replace all occurencies of `/assets/css/style.css` with `/assets/main.css`

* If you are using VSCode, the easiest solution is to use the "search function" as shown in the picture below.

* Then press "replace all"

<img width="1440" alt="Screenshot 2023-04-21 at 20 14 32" src="https://user-images.githubusercontent.com/58792679/233706995-73dca615-417d-4b3d-bdcf-d8b9365f547a.png">
