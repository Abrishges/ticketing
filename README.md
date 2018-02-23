# Ticketing Service
__contact__: Abereham.wodajie@gmail.com

### Assumptions
   * Hold time for the seats is  3 minutes. Seller can configure this value.  If the user doesn't reserve the seats before 3 minutes, then the holds are removed and user has to send
    a request again to hold the seats.
   * Expired Seat Holds can be release by a frequent call from a service or Data base event or Trigger that runs timely. I believe the most efficient is a database event or Trigger
       that run timely. For this home work I create scheduled method that will run every 60 second. The method is inside `SeatHoldServiceImpl` class, `releaseSeats` method

### Design Explanations
Event Tickets or seats can have one of the following states 
  * AVAILABLE, HOLD(temporarily on hold, i.e on the cart), RESERVED (For future purchase) , SOLD

   ### Event: 
Facilitate process of keeping event details up-to-date for all available events for sellers and consumer. Seller sale tickets based on event type.Each event have unique event name eventId. 
In order to ensure that event information displayed on their various web sites (for event discovery), we need to implement event retrieve and update methods. In data base we can have event 
table/schema.  We can also tie events with season. For this project event methods are not implemented
    
  ### AvailableSeats:
AvailableSeats provides seat availability information so that we can serve more relevant results. It contains all the available seats for that event.
eventId, sectionId, rowId, seatNum uniquely identify each available seat. Seat availability services or API calls will provided availability information 
to the caller. The availability of the seats on seller web site seat mapping can also updated frequently to ensure seller doesn't select seats that are
already sold or reserved seats. Having separate service and table in the data base will make availability discovery simple. 
* status: 
    Seller can also make seats active to sell or inactive not to sell specific seat for any reason
* priceCode: 
    Seller can configure prices codes based on their best practice.  we can think of price code as like sections. each section might have    
    different price code. some clients change price code dynamically. a week before event, two day before event, one day before event. they adjust it
    based on dateTime
* reserved:
    when the seat is reserved or on hold we can set this flag to true, so that the seat will no longer be available
    
 ### Best available seats. 
From a search perspective, Ticket buyers not only want multiple seats, but they want the best ones, as well that means that any “Best Available” search algorithm will be searching the exact 
same set of seats (section/row) for all concurrent requests.  so we need to develop very efficient algorithm that looks throw the available seats. Seats can be included as part of best available 
seat based on different criteria. Seller has a few options to play with in order to find the best available seats for the customer. These options can be

 	  - Price levels or range 
      - section of the venue
	  - different ticket types
      - Seats on promotions 
	  - Best seats with low price
	  - offer 
	  - single seats may be left open can also be included as part of the best available 
   
   * For this project, “Best Available” search algorithm is not implemented. I create simple method that return best available seat based on seat number range. Implementation of Best Available 
     seats search algorithm need requirement.  
  
 
 ### SeatHold: 
User can select multiple seats and add them to a shopping cart using Best Available, or seat mapping etc. User is also able to remove any the 
selections from the cart.All seats remain on hold unless seller removes them from the shopping cart or expired release time. User can also select
best available seats and reserve it and it remain as reserved unless seller or buyer removes them from the shopping cart.
Important fields 
  * releaseDatetime: 
        to hold seats on the cart for certain amount of time. 
  *  reserved:
        when true, the seat is reserved for customer. It will stay on reserved state unless seller or buyer removes it regardless of     
              releaseDatetime. 
  -  seatHold object contains seat information and customer information. 
  - Seats can be returned from hold to Available Seats if reserved indicator is false and releaseDatetime is expired. 
  
 ### Ticket  
When a ticket is sold, it will be removed from AvailableSeats and SeatHold and it be populated in ticket. Using api calls or any other means seller
can requests inventory data on the sold seats for that event. The data from this ticketing service will be used to check sold tickets, inventory, 
reporting, seller website seat mapping update etc. So putting sold tickets in different table or dto helps to separate concerns and it makes things
simple. 

* This project is based on maven with spring boot version 1.5

 ### TicketServiceImp class
TicketServiceImpl class conatins implementation methods for the home work. This methods call other service classes like available seats and 
seats hold. Simple Best available method implementation is also under AvailableSeatsServiceImpl which be called by findAndHoldSeats method in
TicketServiceImpl. I also create dumy available seats in AvailableSeatsRepositoryImpl class that will populate five available seats when
the application start
     
 ### TicketServiceImpTest class
Unit test for TicketServiceImp class. Junit mockito is used to for unit test and checked in eclipse using code coverage plugin. 

## Prerequisite

* Maven
	* Check if you have maven installed: `mvn --version`
* IDE
	* Either IntelliJ or Eclipse

*clone or download
  * git clone https://github.com/Abrishges/ticketing.git
  
#### To run the project, Navigate to project directory, ticketing  using terminal or cmd, and use the following cmd

  `mvn spring-boot:run`

#### To run unit tests, Navigate to project directory, ticketing  using terminal or cmd, and use the following cmd

  `mvn test`
  
  From eclipse, you can right click and run unit test
  
#### To test calls , for simplicity use swagger page in your favorite browser. curl commands are also provided below. 
* URL
    * http://localhost:8080/swagger-ui.html#/
    
* ticketing service urls. 
     * http://localhost:8080/swagger-ui.html#/
          


### Test using RESTful Web Services
  we can use swagger page or curl commands to test the code. curl commands are also provided
      ![](/doc/swagger/ticketing_controller.png) 
   
#### Available Seats
  * URL
      * http://localhost:8080/tickets/availableSeats
  ![](/doc/swagger/available_seats.png) 

  * using curl to extract available seat information
  
     ```curl -X GET "http://localhost:8080/tickets/availableSeats" -H "accept: */*"```
   
#### Number of available Seats
  * URL
     * http://localhost:8080/tickets/numOfSeats
      ![](/doc/swagger/numOfSeats.png)
      
     * using curl to extract number of available seat information 
     
       ```curl -X GET "http://localhost:8080/tickets/numOfSeats" -H "accept: */*"```
  
#### Hold best available Seats
  * URL
     * http://localhost:8080/tickets/holdBestAvailableSeats
      ![](/doc/swagger/holdBestAvailableSeats.png)
      
     * using curl to hold best available Seats
     
       ```curl -X GET "http://localhost:8080/tickets/holdBestAvailableSeats?numSeats=2&customerEmail=Abereham.wodajie%40gmail.com" -H "accept: */*"```
       
 ### Reserve best available Seats
 * URL
     * http://localhost:8080/tickets/reserveSeats
       ![](/doc/swagger/reserveSeats.png)
         
   * using curl to reserve best available Seats
   
   ```curl -X GET "http://localhost:8080/tickets/reserveSeats?seatHoldId=1519345131&customerEmail=Abereham.wodajie%40gmail.com" -H "accept: */*"```
           
          
     