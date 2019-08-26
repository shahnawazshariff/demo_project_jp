# demo_jpmorgan
Spring Boot Application for JP Morgan Test in Java 1.8

# Run Instructions 
Do a maven build and run the jar file from command line from the target folder of the application. 

Alternatively open the project in your IDE (as a maven project) and run the class **DemoApplication.java**

# Adding more test data manually
There are some default test loading stubs stored in the in-memory H2 database. After the application has started go to http://localhost:8080/h2 where you can enter the following credentials:
**JDBC url:** jdbc:h2:mem:instructionsDB
**Username:** sa
Password can be left blank

The **INSTRUCTION** table will allow you to enter data manually via the console. Please enter valid data only in the table.

# Application Output in Console
The application produces the following outputs by default to the console when the web service first starts up.

 •  Amount in USD settled incoming everyday  
 •  Amount in USD settled outgoing everyday  
 •  Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing  

# Application Output in Browser for Incomings
Once web service is running, we can check the outputs in the Web Browser in JSON Format. The application runs on localhost:8080 and the urls for the output are as below.

1. List of all entities ranked in reverse order of settled amounts for the current date (based on instruction date = current date)
http://localhost:8080/incoming

2. List of all entities ranked in reverse order of settled amounts for a specific instruction date (date format yyyy-MM-dd)
http://localhost:8080/incoming/2019-08-19 (Please feel free to amend the date when testing)

3. List of incoming total settled amount in USD for current date
http://localhost:8080/incoming/amount

4. List of incoming total settled amount in USD for specific date (date format yyyy-MM-dd
http://localhost:8080/incoming/amount/2019-08-19

# Application Output in Browser for Outgoings

1. List of all entities ranked in reverse order of settled amounts for the current date (based on instruction date = current date)
http://localhost:8080/outgoing

2. List of all entities ranked in reverse order of settled amounts for a specific instruction date (date format yyyy-MM-dd)
http://localhost:8080/outgoing/2019-08-18 (Please feel free to amend the date when testing)

3. List of incoming total settled amount in USD for current date
http://localhost:8080/outgoing/amount

4. List of incoming total settled amount in USD for specific date (date format yyyy-MM-dd
http://localhost:8080/outgoing/amount/2019-08-18




