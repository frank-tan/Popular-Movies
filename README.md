# Popular Movies Android App

`Popular Movies` is an Android app which helps you to discover the latest popular and top rated movies. You can flip through movie posters, check movie details, watch movie trailers, read other people's reviews and create a list of your favourite movies. It is designed and optimised for both Android phones and tablets. 

This pages documented the technical design of the app. For the functioanl details and screenshots, please visit [Frank's Popular Movies app](http://frank-tan.github.io/Popular-Movies/).

## Data Retrieval Logic

This app retrieves a Movie list using a SyncAdapter. The SyncAdapter performs an immediate synchronisation when the user opens the app for the first time and when a new version of this app with data schema changes is installed and opened. It performs subsequent synchronisation every 24 hours in the background. The synchronisation retrieves a full genre list and a movie list by popularity and by rating. The movie list retrieved includes movie details such as title, language, poster path, backdrop path, vote count, vote average, genres, etc. 

Movie trailer addresses and reviews are retrieved using an IntentService when movie detail fragment is loaded. 

Movie poster images, backdrop images and trailer thumbnail images are retrieved when the corresponding fragment is loaded. Poster images and backdrop images are retrieved using [Picasso](http://square.github.io/picasso/) and trailer thumbnail is retrieved using [Youtube Android API](https://developers.google.com/youtube/android/player/). 

## Caching and Offline Use

The app saves movie details, genres, trailer address, reviews and favourites in the SQLite database on the device. Movie poster image and backdrop image are cached on the device using OKHttp. So as long as the data are retrieved once, the app can still show saved/cached data and work in an offline mode.

## Playing Trailers

When Youtube Android app is installed on the device, the app uses [Youtube Android API](https://developers.google.com/youtube/android/player/) to invoke Youtube app for playing trailers. If the Youtube app is not available on the device, the app loads the Youtube video URL in a web browser.

## Data Schema

The diagram shows the data schema of the app's SQLite database.

![Alt text](content_provider_generator/popular-movies-db.png?raw=true "Database Schema")

The app is developed for [Udacity Android Developer Nanodegree](https://www.udacity.com/course/android-developer-nanodegree--nd801).
