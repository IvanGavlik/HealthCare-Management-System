## Steps to run application
* start DB using docker
  * in the terminal go to src/main/resources
  * run docker-compose rm -v -f db && docker-compose up
* start the application
  * java main is here org/hcms/Main.java 

## Steps to shout down application
after you exit app do not forget to shout down DB docker image  

## TODO
* exception handling 
* replace statement with prepared statmenet
* naming convention - code DB UI
* logger
* validation layer (including constraint DB and business case validator)
* tests -junit, integration e2e
* external party lib - validate credit card
* enums
* patient feedback vs patient service SRP -> also modularizazion
* connect feedback with Appointment (feedbcak for only done appontments)
* create diagrams
* standardize output create and use one class
* where i need singletons, and where more object (should go with the factor ? )
