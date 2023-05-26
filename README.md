# HealthCare-Management-System (hms)

This repo contains source code for blog post [here]()

Here you can find 
* Application requirements
* Steps to run application
* Login
* Steps to shout down application
* Configure application
* TODO list

Also take a look 
* application description

## Application requirements
* java 11 or higher
* docker
* maven
* mysql DB (optional - only if you want custom DB)

## Steps to run application
* start DB using docker
  * in the terminal go to ``src/main/resources``
  * ``docker-compose up``, if you want to start with fresh DB run ``docker-compose rm -v -f db && docker-compose up`` 
* build app with maven
  * run ``mvn clean assembly:assembly`` from the project root folder ``/HealthCare-Management-System``
* start the application
  * java main is here ``org/hcms/Main.java``
  * or run the jar file ``java -jar target/hms-1.0.0-jar-with-dependencies.jar`` from the project root folder ``/HealthCare-Management-System``
  * you can pass configuration file to the application see [here](#ca)

## Login
* in the main menu enter 1 to select ADMIN credentials username: *admin* password: *123*
* in the main menu enter 2 to select PATIENT credentials id: *1* password: *One*
* in the main menu enter 3 to select DOCTOR credentials id: *1* password: *One*

## Steps to shout down application
* in the main menu enter *5* to exit
* do not forget to shout down DB docker image

## Configure application

<a id="ca"></a>

### Custom DB
Application is using mysql DB, if not provided default DB is used.   
You can create your own DB and pass its connection params using configuration file. 

File format  
db.url=value  
db.user=value  
db.password=value  

DB schema is in the ``src/main/resources/scripts/schema.sql``

### Set up application admin
Application default admin credentials are username: *admin* password: *123*  
You can change this using configuration file.

File format  
admin.name=value  
admin.password=value  


### Configuration file
It has to have DB configuration and admin configuration.  
If one is missing application will not work.

Configuration File is passed as parameter to java application.
* When using terminal ``run java -jar target/hms-1.0.0-jar-with-dependencies.jar pathToTheFile``.  If file is at ``desktop/config.file`` then pass ``java -jar target/hms-1.0.0-jar-with-dependencies.jar /desktop/config.file``
* when using IDE add parameter to run setting (should have terminal params). If you can not find it google it. 

You can copy this to your configuration file.

db.user=value  
db.password=value  
admin.name=value  
admin.password=value


## TODO
* exception handling 
* replace statement with prepared statement
* naming convention - code DB UI
* logger
* validation layer (including constraint DB and business case validator)
* tests -junit, integration e2e
* external party lib - validate credit card
* enums
* patient feedback vs patient service SRP -> also modularization
* connect feedback with Appointment (feedback for only done appointments)
* create diagrams
* standardize output create and use one class
* where i need singletons, and where more object (should go with the factor ? )
* patientTerminalView.showDoctors save as adminTerminalView
