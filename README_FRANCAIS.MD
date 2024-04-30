## Informations générales


[lien du Github](https://github.com/Loxed/PersonaPlayApplication)

## Installation de MySQL sur Windows

1. Téléchargez le programme d'installation de MySQL pour Windows depuis le site officiel de MySQL.
2. Exécutez le programme d'installation téléchargé et suivez les instructions à l'écran pour installer MySQL sur votre machine.
3. Pendant le processus d'installation, assurez-vous de sélectionner l'option d'installation des composants serveur et client de MySQL.
4. Lorsque vous y êtes invité, définissez un mot de passe pour l'utilisateur "root", qui sera utilisé pour se connecter au serveur MySQL.
5. Une fois l'installation terminée, vous pouvez vérifier que MySQL est installé en ouvrant une invite de commande ou un terminal PowerShell et en exécutant la commande `mysql`.

## Création d'une base de données et d'un schéma MySQL sous Windows

1. Ouvrez le client de ligne de commande MySQL ou un outil comme phpMyAdmin.
2. Connectez-vous au serveur MySQL fonctionnant sur le port 3306 avec le nom d'utilisateur `root` et le mot de passe que vous avez défini lors de l'installation en exécutant la commande suivante :

```sql
mysql -u root -p -h localhost -P 3306
```

2. bis Si vous utilisez phpMyAdmin ou mySQL Workbench, vous pouvez vous connecter au serveur MySQL en entrant les informations suivantes :

- Hôte : `localhost`
- Nom d'utilisateur : `root`
- Mot de passe : `root`

3. Créez une nouvelle base de données en exécutant la commande suivante :

```sql
CREATE DATABASE personaplay ; USE personaplay ;
```

4. Créez des tables en utilisant l'instruction create dans le fichier [schema.sql](src%2Fmain%2Fresources%2Fcom%2Fexample%2Fpersonaplayfront%2FDatabase%2Fschema.sql).
5. Insérer des données à l'aide de l'instruction insert dans le fichier [insert.sql](src%2Fmain%2Fresources%2Fcom%2Fexample%2Fpersonaplayfront%2FDatabase%2Finsert.sql).

## Exécution de l'application

1. Ouvrir le projet dans votre IDE.
2. Exécutez la méthode main de `PersonaPlayApplication` dans la classe `com.example.personaplay.PersonaplayApplication`.

Les autres classes d'application sont utilisées pour initialiser les valeurs avec l'API et tester les fenêtres individuelles.
Evitez d'exécuter ApiApplication, elle est utilisée pour initialiser la base de données avec l'API et utilise beaucoup de requêtes.

3. Pour vous connecter, créez un compte en vous inscrivant. Assurez-vous de recevoir l'e-mail de confirmation et entrez le code dans la fenêtre de confirmation.

## Construit avec

* [Javafx](https://openjfx.io/) - JavaFX est une plate-forme logicielle pour créer et fournir des applications de bureau, ainsi que des applications Internet riches (RIA) qui peuvent fonctionner sur une grande variété d'appareils.
* Hibernate](https://hibernate.org/) - Hibernate ORM est un outil de mappage objet-relationnel pour le langage de programmation Java. Il fournit un cadre pour la mise en correspondance d'un modèle de domaine orienté objet avec une base de données relationnelle.
* [Okhttp](https://square.github.io/okhttp/) - Un client HTTP+HTTP/2 pour les applications Android et Java.
* MySQL](https://www.mysql.com/) - MySQL est un système de gestion de base de données relationnelle (SGBDR) open-source.
* [Maven](https://maven.apache.org/) - Maven est un logiciel de gestion de projet.
