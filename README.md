# HealthCare-Management-System (hms)

This repo contains source code for blog post [here]()

## Table Of Content
* [Description](#de)
* [Application requirements](#ar)
* [Steps to run application](#ru)
* [Login](#lo)
* [Steps to shout down application](#sh)
* [Configure application](#co)
* [Code style guide](#gu)
* [TODO list](#to)


## Description <a id="de"></a>
E-HealthCare-Management-System is a console based application which is built using java.  
This application helps in management of Patients, doctors, admin in a easy and comfortable way.  
Using this Application patients can quickly Sign up, Login, view his/her profile, view doctors, book Appointment, view Report, choose doctor, view Appointments, give feedback, pay online and logout.  
Admin can add Doctors,view patients list, view Doctors list,remove doctors, see feedback given by patients,view reports,logout.  
Doctor can login, view profile, viewAppointments, Attend Patients and logout.

## Application requirements <a id="ar"></a>
* java 11 or higher
* docker
* maven
* mysql DB (optional - only if you want custom DB)

## Steps to run application <a id="ru"></a>
* start DB using docker
  * in the terminal go to ``src/main/resources``
  * ``docker-compose up``, if you want to start with fresh DB run ``docker-compose rm -v -f db && docker-compose up`` 
* build app with maven
  * run ``mvn clean assembly:assembly`` from the project root folder ``/HealthCare-Management-System``
* start the application
  * java main is here ``org/hcms/Main.java``
  * or run the jar file ``java -jar target/hms-1.0.0-jar-with-dependencies.jar`` from the project root folder ``/HealthCare-Management-System``
  * you can pass configuration file to the application see [here](#ca)

## Login  <a id="lo"></a>
* in the main menu enter 1 to select ADMIN credentials username: *admin* password: *123*
* in the main menu enter 2 to select PATIENT credentials id: *1* password: *One*
* in the main menu enter 3 to select DOCTOR credentials id: *1* password: *One*

## Steps to shout down application <a id="sh"></a>
* in the main menu enter *5* to exit
* do not forget to shout down DB docker image

## Configure application <a id="co"></a>

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

## Code style guide <a id="gu"></a>

### Naming
Follow the camel case convention, for the code and the DB

### Curly Braces

```java
class MyClass { 
    public MyClass() {
        
    }
}
```

### Indentation
* One tab (4 spaces)
* Add a space between a keyword, operator

### Project specific
* keep classes shy (do not expose use final, check access modifies, check constructor...)
* keep classes immutable
* class ending with ```TerminalView``` and ```Portal``` are used for terminal display (UI)
* interface should have static variable ```DEFAULT_INSTANCE``` 
* classes that maps data tables should be in ```org.hcms.data``` package
* keep business logic in service not in UI


## TODO <a id="to"></a>
* exception handling 
* replace statement with prepared statement
* naming convention - code for DB 
* logger
* validation layer (including constraint DB and business case validator)
* tests - junit, integration, e2e
* external party lib - validate credit card
* replace string with enums
* standardize output create and use one class

