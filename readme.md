## Informations générales


[lien du Github](https://github.com/Loxed/PersonaPlayApplication)

[lien du Canva](https://www.canva.com/design/DAFgLTpyY0Y/vBX3uOBSDZyuMp61s8DtiQ/edit?utm_source=shareButton&utm_medium=email&utm_campaign=designshare)

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
CREATE DATABASE personaplay; USE personaplay;
```

4. Create tables using create statement in file [schema.sql](src%2Fmain%2Fresources%2Fcom%2Fexample%2Fpersonaplayfront%2FDatabase%2Fschema.sql).
5. Insert data using insert statement in file [insert.sql](src%2Fmain%2Fresources%2Fcom%2Fexample%2Fpersonaplayfront%2FDatabase%2Finsert.sql).

## Running the application

1. Open the project in your IDE.
2. Run the `PersonaPlayApplication`'s main method in the `com.example.personaplay.PersonaplayApplication` class.

The other Application classes are used to init values with API and test individual windows.
Avoid running ApiApplication, it is used to init the database with the API and uses a lot of requests.

3. To log in, create an account with sign up. Make sure you receive the confirmation email and enter the code in the confirmation window.

## Built With

* [Javafx](https://openjfx.io/) - JavaFX is a software platform for creating and delivering desktop applications, as well as rich internet applications (RIAs) that can run across a wide variety of devices.
* [Hibernate](https://hibernate.org/) - Hibernate ORM is an object-relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database.
* [Okhttp](https://square.github.io/okhttp/) - An HTTP+HTTP/2 client for Android and Java applications.
* [MySQL](https://www.mysql.com/) - MySQL is an open-source relational database management system (RDBMS).
* [Maven](https://maven.apache.org/) - Maven is a software project management and comprehension tool.


## Authors


* [Leopold Rombaut](https://www.github.com/loxed) - Back-end, Application Navigation, Database, API Integration, UI Design

* [Sylvestre Dongmo Melong](#) - Admin Page UI

* [Yasmine Harzallah](#) - Front-end, Home Page UI

* [Harold Kemgang](#) - Front-end, Login/Sign Up UI