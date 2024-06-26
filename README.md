# Music Platform API - Spring Boot Project

This repository contains the source code for a Music Platform API implemented using Spring Boot. The API allows users to interact with music-related data such as songs, artists, albums, genres, playlists, and user favorites.
- Built-in MVC configuration implemented 
- Websocket used for real-time messaging
- Please check the front-end repository [here](https://github.com/yasinunl/MusicPlayerFrontEnd)
## Database Schema(MySQL)
![img.png](img.png)
## DTO examples
If you check SongService and other service classes, I used DTO for UserService to get variables as list of integers not list of map. It is the difference between them:<br />

````java
public class UserDTO {
...
// It takes list of integers
private List<Integer> genre;
    ... }
````

````java
public class Playlist {
...
// It takes list of Song entity
private List<Song> song;
    ...}
````

| Add genre to song | `{"id":3,"genre":[1,2,3,4]} `                 |
|-------------------|-----------------------------------------------|
| Add song to playlist | `{ "id": 10,"song": [{"id": 1}, {"id": 2}]} ` |
## Endpoint list 
![img_2.png](img_2.png) ![img_3.png](img_3.png)![img_4.png](img_4.png) ![img_5.png](img_5.png)
![img_6.png](img_6.png) ![img_7.png](img_7.png)