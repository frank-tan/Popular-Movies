#Content Provider Generator

This folder contains configuration files for generating a content provider. The project use [BoD/android-contentprovider-generator](https://github.com/BoD/android-contentprovider-generator) to generate a content provider.

## Steps

To generate content provider,

1. Download latest android-contentprovider-generator from [here](https://github.com/BoD/android-contentprovider-generator/releases)
2. Run `java -jar android_contentprovider_generator-1.9.3-bundle.jar -i <input folder> -o <output folder>`

  * Input folder: where to find _config.json and your entity json files
  * Output folder: where the resulting files will be generated

## Database Schema

![Alt text](popular-movies-db.png?raw=true "Database Schema")
