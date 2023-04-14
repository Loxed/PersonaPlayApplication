## Installing MySQL on Windows

1. Download the MySQL installer for Windows from the official MySQL website.
2. Run the downloaded installer and follow the on-screen instructions to install MySQL on your machine.
3. During the installation process, make sure to select the option to install the MySQL server and client components.
4. When prompted, set a password for the `root` user, which will be used to log in to the MySQL server.
5. After the installation is complete, you can verify that MySQL is installed by opening a command prompt or PowerShell terminal and running the `mysql` command.

## Creating a MySQL Database and Schema on Windows

1. Open the MySQL command line client or a tool like phpMyAdmin.
2. Connect to the MySQL server running on port 3306 with the username `root` and password you set during installation by running the following command:

```sql
mysql -u root -p -h localhost -P 3306
```

2. bis If you are using phpMyAdmin or mySQL Workbench, you can connect to the MySQL server by entering the following information:

- Host: `localhost`
- Username: `root`
- Password: `root`

3. Create a new database by running the following command:

```sql
CREATE DATABASE personaplay;
```

4. Create a new table in the database called Medias by executing the file below:

```mysql
CREATE TABLE `medias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imdb_id` varchar(9) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `actors` varchar(100) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `plot` mediumtext,
  `visible` tinyint(1) DEFAULT '1',
  `available` tinyint(1) DEFAULT '0',
  `media_location` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=433 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

5. Insert the following data into the Medias table:

```mysql
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('1', 'tt0111161', 'The Shawshank Redemption', 'https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_SX300.jpg', 'Drama', '1994', 'Tim Robbins, Morgan Freeman, Bob Gunton', 'Frank Darabont', 'Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('2', 'tt0068646', 'The Godfather', 'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'Crime, Drama', '1972', 'Marlon Brando, Al Pacino, James Caan', 'Francis Ford Coppola', 'The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('3', 'tt0468569', 'The Dark Knight', 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg', 'Action, Crime, Drama', '2008', 'Christian Bale, Heath Ledger, Aaron Eckhart', 'Christopher Nolan', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('4', 'tt0071562', 'The Godfather Part II', 'https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'Crime, Drama', '1974', 'Al Pacino, Robert De Niro, Robert Duvall', 'Francis Ford Coppola', 'The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('5', 'tt0050083', '12 Angry Men', 'https://m.media-amazon.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg', 'Crime, Drama', '1957', 'Henry Fonda, Lee J. Cobb, Martin Balsam', 'Sidney Lumet', 'The jury in a New York City murder trial is frustrated by a single member whose skeptical caution forces them to more carefully consider the evidence before jumping to a hasty verdict.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('6', 'tt0108052', 'Schindler''s List', 'https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'Biography, Drama, History', '1993', 'Liam Neeson, Ralph Fiennes, Ben Kingsley', 'Steven Spielberg', 'In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('7', 'tt0167260', 'The Lord of the Rings: The Return of the King', 'https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'Action, Adventure, Drama', '2003', 'Elijah Wood, Viggo Mortensen, Ian McKellen', 'Peter Jackson', 'Gandalf and Aragorn lead the World of Men against Sauron''s army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('8', 'tt0110912', 'Pulp Fiction', 'https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'Crime, Drama', '1994', 'John Travolta, Uma Thurman, Samuel L. Jackson', 'Quentin Tarantino', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('9', 'tt0060196', 'The Good, the Bad and the Ugly', 'https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg', 'Adventure, Western', '1966', 'Clint Eastwood, Eli Wallach, Lee Van Cleef', 'Sergio Leone', 'A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('10', 'tt0109830', 'Forrest Gump', 'https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg', 'Drama, Romance', '1994', 'Tom Hanks, Robin Wright, Gary Sinise', 'Robert Zemeckis', 'The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood swe...', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('11', 'tt0137523', 'Fight Club', 'https://m.media-amazon.com/images/M/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg', 'Drama', '1999', 'Brad Pitt, Edward Norton, Meat Loaf', 'David Fincher', 'An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('12', 'tt0167261', 'The Lord of the Rings: The Two Towers', 'https://m.media-amazon.com/images/M/MV5BZGMxZTdjZmYtMmE2Ni00ZTdkLWI5NTgtNjlmMjBiNzU2MmI5XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'Action, Adventure, Drama', '2002', 'Elijah Wood, Ian McKellen, Viggo Mortensen', 'Peter Jackson', 'While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron''s new ally, Saruman, and his hordes of Isengard.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('13', 'tt1375666', 'Inception', 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg', 'Action, Adventure, Sci-Fi', '2010', 'Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page', 'Christopher Nolan', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('14', 'tt0133093', 'The Matrix', 'https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'Action, Sci-Fi', '1999', 'Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss', 'Lana Wachowski, Lilly Wachowski', 'When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('15', 'tt0099685', 'Goodfellas', 'https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDgtN2RjYy00YzM1LWI4ZmQtMjIwYjFjNmI3ZGEwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 'Biography, Crime, Drama', '1990', 'Robert De Niro, Ray Liotta, Joe Pesci', 'Martin Scorsese', 'The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('16', 'tt0073486', 'One Flew Over the Cuckoo''s Nest', 'https://m.media-amazon.com/images/M/MV5BZjA0OWVhOTAtYWQxNi00YzNhLWI4ZjYtNjFjZTEyYjJlNDVlL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg', 'Drama', '1975', 'Jack Nicholson, Louise Fletcher, Michael Berryman', 'Milos Forman', 'In the Fall of 1963, a Korean War veteran and criminal pleads insanity and is admitted to a mental institution, where he rallies up the scared patients against the tyrannical nurse.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('17', 'tt0114369', 'Se7en', 'https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'Crime, Drama, Mystery', '1995', 'Morgan Freeman, Brad Pitt, Kevin Spacey', 'David Fincher', 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('18', 'tt0038650', 'It''s a Wonderful Life', 'https://m.media-amazon.com/images/M/MV5BZjc4NDZhZWMtNGEzYS00ZWU2LThlM2ItNTA0YzQ0OTExMTE2XkEyXkFqcGdeQXVyNjUwMzI2NzU@._V1_SX300.jpg', 'Drama, Family, Fantasy', '1946', 'James Stewart, Donna Reed, Lionel Barrymore', 'Frank Capra', 'An angel is sent from Heaven to help a desperately frustrated businessman by showing him what life would have been like if he had never existed.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('19', 'tt0047478', 'Seven Samurai', 'https://m.media-amazon.com/images/M/MV5BNWQ3OTM4ZGItMWEwZi00MjI5LWI3YzgtNTYwNWRkNmIzMGI5XkEyXkFqcGdeQXVyNDY2MTk1ODk@._V1_SX300.jpg', 'Action, Drama', '1954', 'Toshirô Mifune, Takashi Shimura, Keiko Tsushima', 'Akira Kurosawa', 'Farmers from a village exploited by bandits hire a veteran samurai for protection, who gathers six other samurai to join him.', '1', '0', 'NULL');
INSERT INTO medias (id, imdb_id, title, poster, genre, year, actors, director, plot, visible, available, media_location) VALUES ('20', 'tt0102926', 'The Silence of the Lambs', 'https://m.media-amazon.com/images/M/MV5BNjNhZTk0ZmEtNjJhMi00YzFlLWE1MmEtYzM1M2ZmMGMwMTU4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'Crime, Drama, Thriller', '1991', 'Jodie Foster, Anthony Hopkins, Lawrence A. Bonney', 'Jonathan Demme', 'A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.', '1', '0', 'NULL');
```

## Running the application

1. Open the project in your IDE.
2. Run the `PersonaPlayApplication`'s main method in the `com.example.personaplay.PersonaplayApplication` class.

The other Application classes are used to init values with API and test individual windows.
Avoid running ApiApplication, it is used to init the database with the API and uses a lot of requests.

`WindowTesterApplication` is used to test individual windows without the need to run the whole application.It's very useful to test the UI. functionalities.

3. To temporarily log in, use the following credentials which are defined locally:

```
username: alice/bob
password: password1/password2
```
```Todo: implement users and roles into the app```

## Built With

* [Javafx](https://openjfx.io/) - JavaFX is a software platform for creating and delivering desktop applications, as well as rich internet applications (RIAs) that can run across a wide variety of devices.
* [Hibernate](https://hibernate.org/) - Hibernate ORM is an object-relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database.
* [Okhttp](https://square.github.io/okhttp/) - An HTTP+HTTP/2 client for Android and Java applications.
* [MySQL](https://www.mysql.com/) - MySQL is an open-source relational database management system (RDBMS).
* [Maven](https://maven.apache.org/) - Maven is a software project management and comprehension tool.

[//]: # ()
[//]: # (## Authors)

[//]: # ()
[//]: # (* [Leopold Rombaut]&#40;https://www.github.com/loxed&#41; - Back-end, Application Navigation, Database, API Integration, UI Design)

[//]: # (* [Sylvestre Dongmo Melong]&#40;#&#41; - Back-end, Database, Admin Panel, UI Design)

[//]: # (* [Yasmine Harzallah]&#40;#&#41; - Front-end, Home Page UI)

[//]: # (* [Harold Kemgang]&#40;#&#41;)