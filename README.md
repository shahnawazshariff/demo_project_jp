# demo_jpmorgan
Spring Boot Application for JP Morgan Test in Java 1.8

# Run Instructions 
Do a maven build and run the jar file from command line from the target folder of the application. 

Alternatively open the project in your IDE (as a maven project) and run the class **DemoApplication.java**

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




