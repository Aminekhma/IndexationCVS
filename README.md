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
Ouvrir le dossier <b>BackEnd_IndexationCVS</b> contenu dans IndexationCVS\BackEnd_IndexationCVS\src\main\java\fr\sorbonne\IndexationCVS (avec l'éditeur VSCode par exemple) et lancer l'exécution de la classe "IndexationCvsApplication.java" en utilisant la commande `Run java` qui se trouve en haut a droite de l'éditeur.

Les CVs sont contenus dans le dossier \BackEnd_IndexationCVS\src\main\java\fr\sorbonne\IndexationCVS\service\assets au format .pdf (qui sera converti en json à l'aide de la classe PDFManager).
Pour ajouter les données d'un CV, il faudra donc ajouter le fichier pdf dans le dossier assets.

### Front-End
Ouvrir le dossier <b>FrontEnd_IndexationCVS</b> avec un terminal et exécuter ces deux instruction : 

1.`npm install`

2.`npm run serve`

> Utilisation du Site 

* <b>Fenêtre "Refresh Data" : </b>
    1ère étape à effectuer pour pouvoir ajouter les données dans elasticsearch. L'utilisateur doit attendre que les données s'ajoutent au site.

* <b>Fenêtre "Search a profil" : </b>
    Permet de faire une recherche par mot-clé à l'aide de la barre de recherche. Le résultat sera une liste de CV contenant ces mots recherchés.

    PS: Utiliser le bouton <b>Go</b> et non la touche entrée pour effectuer la recherche.

* <b>Fenêtre "All profil" : </b>
    Affiche tous les CVs contenus dans la base de données du site.

* <b>Fenêtre "Add CV" : </b>
    Ajoute le CV correspondant à son chemin rensigné par l'utilisateur (faire un Refresh Data à la suite de cela). Echoue si le chemin est incorrect.

### Logstash

<b>Installation</b> : https://www.elastic.co/guide/en/logstash/current/installing-logstash.html

Lancer __Logstash__ avec cette commande en lui précisant le chemin de la configuration : 
* `./bin/logstash -f ../IndexationCVS/logstash_conf.conf`
