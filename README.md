To do this project I decided to use an MVVM design using AndroidViewModel and LiveData to communicate the Domain data into the views. 

For the network connections I have created a separated module called 'Repository' where using Retrofit2, OkHttp and Gson the app retrieves from the endpoint, caches the response and parses the data into models.

To separate the UI from the data I created a DataManager that acts as a bridge between the UI and the repository. The data manager not only calls the repository to retrieve data but also uses model mappers to convert the repository model into a domain model to completely separate the knowledge between the two modules.

Throughout the whole app, I use abstraction and single responsibility to provide a more maintainable code and avoiding mixed dependencies.

On the UI side, I use a single activity pattern because of the simplicity of the app and a 'NewInstance' pattern for fragment creation.
In terms of UI scalability, the UI doesn't know if the team and players are from the NBA, NFL or any other sport, it uses the objects Team and Player, therefore, this will allow us to re-use this UI for different sports.
