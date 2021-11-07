# PROJET 2 - DAAR
>  ## Réalisé par :
> * <b>Amine KHEDDAR</b> 
> * <b>Sofiane BELKHIR</b>
> * <b>David HERZOG</b>



> 🚀 Le code du projet est divisé en 2 répertoires.
* 📁 <b>BackEnd_IndexationCVS</b> 
* 📁 <b>FrontEnd_IndexationCVS</b>
 

### Exécution du code
#### ElasticSearch
<b>Installation</b> : https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html

Lancer ElasticSerach en utilisant cette commande :  `./bin/elasticsearch` (ou elasticsearch.bat)

#### Kibana 
<b>Installation</b> : https://www.elastic.co/guide/en/kibana/current/index.html

Lancer ElasticSerach en utilisant cette commande :  `./bin/kibana` (ou kibana.bat)

### Back-End
Ouvrir le dossier <b>BackEnd_IndexationCVS</b> avec comme éditeur VSCode et lancer la classe "IndexationCvsApplication.java" en utilisant la commande `Run java` qui se trouve en haut a droite de l'éditeur.

Les CV sont contenu dans le fichier \BackEnd_IndexationCVS\src\main\java\fr\sorbonne\IndexationCVS\service\assets au format .pdf (qui sera convertit en json à l'aide de la classe PDFManager).
Pour ajouter les données d'un CV, il faudra donc ajouter le fichier pdf dans le dossier assets.

### Front-End
Ouvrir le dossier <b>FrontEnd_IndexationCVS</b> avec un terminal et exécuter ces deux instruction : 

1.`npm install`

2.`npm run serve`

> Utilisation du Site 

* <b>Fenetre "Refresh Data" : </b>
    1er étape a effectué pour pouvoir ajouter les données dans elasticsearch. L'utilisateur doit attendre que les données s'ajoutent au site.

* <b>Fenetre "Search a profil" : </b>
    Permet de faire une recherche par mot clé à l'aide de la barre de recherche. Le résultat sera une liste de CV contenant ses mots

* <b>Fenetre "All profil" : </b>
    Affiche tous les CV contenu dans la base de données du site.

* <b>Fenetre "Add CV" : </b>
    Ajoute le CV correspondant a son chemin rensigner par l'utilisateur (faire un Refresh Data à la suite de cela). Echoue si le chemin est mauvais.

### Logstash

<b>Installation</b> : https://www.elastic.co/guide/en/logstash/current/installing-logstash.html

Lancer __Logstash__ avec cette commande en lui précisant le chemin de la configuration : 
* `./bin/logstash -f ../IndexationCVS/logstash_conf.conf`
