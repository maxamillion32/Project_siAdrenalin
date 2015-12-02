# Project siAdrenalin
an Android app to explore the adrenaline/extreme sports of Slovenia. Developed in second and third year of college. 
Published diploma thesis on the research, development, testing and publishing the application can be found here (in Slovenian language):
https://dk.um.si/IzpisGradiva.php?id=54440&lang=slv

An AndroidStudio project.

Uses the Google spreadsheet tables as data storage. Data is being downloaded and maintained via Http REST request using GQL (Google Query Language).
Some of the apps main features are:
- Material design
- Background data synchronization at app launch via several AsynchTasks.
- Two different uses of Google map integration. One standard map with extra navigation feature and one to show only the locations within an user
  defined range. Both use Google API integration. Navigation feature uses Google Directions API, nearby uses the Google Distance Matrix API.
- Multilingual support (Slovenian, German and English language)

The apps Google Play link: https://play.google.com/store/apps/details?id=com.siadrenalin
