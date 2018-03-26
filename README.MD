Afin de d'exécuter l'application Climbers, il faut procéder à certaines configurations.
Il faut que vous créeiez une base de donnée gràce au fichier de scripts SQL "DBCreation.sql" dans le dossier "sql scripts"
Vous pouvez également insérer un jeu de données grâce à tous les autres fichiers de script sql inclus dans le même dossier

Afin de vous connecter à votre base de donnée il faudra saisir les informations de connexion dans le fichier de configuration config.properties:
Par défaut, les bases de données Postgresql sont paramétrées sur le port 5432. Vous devez procéder comme suit:

spring.datasource.url= jdbc:postgresql://localhost:5432/nomdelabasededonnees
spring.datasource.username=votreidentifiant
spring.datasource.password=votremotdepasse
spring.jpa.hibernate.ddl-auto= update
