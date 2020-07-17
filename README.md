
# Avatar Character Database Android  
  
This project is still under active development and isn't yet considered complete.
***  
### Acknowledgements  
The current version of the app uses a REST API which is not developed by me. I do plan on creating my own in future to accompany this project but all credit for the current version goes to [GitHub user paigeegorry](https://github.com/paigeegorry/last-airbender-api).   
  
The current version of the REST API can be found at [last-airbender-api.herokuapp.com](https://last-airbender-api.herokuapp.com/)  
***  
### Overview  
The main goal of this project is to showcase how I like to structure my Android apps. This project shows off the layers of code I use, the libraries I most commonly use and how I tie these together in an effort to create an app which is fast and code which is maintainable and easy to follow.  
  
The app being based around Avatar is just because it's a brilliant TV show :)  
***  
### Tech used  
 - Kotlin  
 - MVVM  
 - Coroutines  
 -- Flow  
 - Retrofit + Moshi converter  
 - Moshi  
 - Dagger Hilt  
 - Glide  
 - Android Jetpack  
 -- Room  
 -- Navigation  
 - Mockk  
***  
### Code Architecture  
This project uses the MVVM architecture as recommended by Google.  
  
I have also added in the concept of processors and converters which are in charge of taking network and database responses and converting them to a form more suitable to be used by the next layer up in the architecture.  

For example there is a `CharacterListResponseProcessor` class found in the `repo.character` package which converts the network response `CharacterResponse`into a `CharacterEntity` which can be saved in the database. The class `CharacterListItemUiModelProcessor`found in the`ui.character.list.model`package is then used to convert a `CharacterEntity`into a `CharacterListItemUiModel`.
***  
I am always looking to improve my code and take on new suggestions so if you see anything in there you think could be improved upon then feel free to create an issue!