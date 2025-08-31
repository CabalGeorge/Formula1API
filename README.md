# Formula1API

 - In order to proceed to the running of the application you first need to have installed Docker & Docker Compose.
 - After the installation you need to adjust the variables from the .env file as you can see in this example (Also uncomment the lines):
                  MYSQL_ROOT_PASSWORD=verysecurepassword
                  MYSQL_DATABASE=formula1
                  MYSQL_USERNAME=mysqlusername
                  MYSQL_PASSWORD=myverysecurepassword
 - After changing the variables, open a terminal/cmd in the root folder of the application and execute the following command: docker-compose up --build
 - Now the application should be started on http://localhost:8080/api/f1. 
 - In the resources file there is already a postman collection attached with calls for every endpoint, otherwise you can also call the endpoints using cURL.
 - To shut down the application you will execute the following command: docker-compose down