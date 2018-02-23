# Ticketing Service
__contact__: Abereham.wodajie@gmail.com

### Assumptions
   * Hold time for the seats is  3 minutes. Seller can configure this value.  If the user doesn't reserve the seats before 3 minutes, then the holds are removed and user has to send
    a request again to hold the seats.
   * Expired Seat Holds can be release by a frequent call from a service or Data base event or Trigger that runs timely. I believe the most efficient way is to run a database event or Trigger
       that run timely. For this home work I create scheduled method that will run every 60 second. The method is inside `SeatHoldServiceImpl` class, `releaseSeats` method

### Design Explanations
Event Tickets or seats can have one of the following status 
  * AVAILABLE, HOLD(temporarily on hold, i.e on the cart), RESERVED (For future purchase) , SOLD

   ### Event: 
Event class facilitate process of keeping event details up-to-date for all available events for sellers and consumer. Seller sale tickets based on event type.Each event have unique event name and eventId. 
In order to ensure that event information displayed on their various web sites (for event discovery), we need to implement event retrieve and update methods. In data base we can have event 
table/schema.  We can also tie events with season. For this project event methods are not implemented
    
  ### AvailableSeats:
AvailableSeats class provides seat availability information so that we can serve more relevant results. It contains all the available seats for that event.
EventId, sectionId, rowId, and seatNum uniquely identify each available seat. Seat availability services or API calls will provided availability information 
to the caller. The availability of the seats on seller web site seat mapping can also be updated frequently to ensure seller doesn't select seats that are
already sold or reserved seats. Having separate service and table in the data base will make availability discovery simple. 

  Additional important fields 
* status: 
    Seller have controller to seats. He/she can make seats active to sell or inactive not to sell a specific seat for any reason
* priceCode: 
    Seller can configure prices codes based on their best practice.  we can think of price code as like sections. Each section might have different price code. Sellers can change price code dynamically a week before event, two day before event, one day before event. They can adjust it based on demand. 
* reserved:
    when the seat is reserved or on hold we set this flag to true, so that the seat will no longer be available
    
 #### Best available seats. 
From a search perspective, ticket buyers not only want multiple seats, but they want the best ones. This means that any “Best Available” search algorithm will be searching the exact 
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
User can select multiple seats and add them to a shopping cart using Best Available, or seat mapping etc. User also able to remove any the 
selections from the cart. All seats remain on hold unless seller removes them from the shopping cart or expired release time. User can also select
best available seats and reserve it and it remain as reserved unless seller or buyer removes them.
Important fields 
 
   Additional important fields 
  * releaseDatetime: 
        To hold seats on the cart for 3 mints. Seller can configure this value 
  *  reserved:
        when true, the seat is reserved for customer. It will stay on reserved state unless seller or buyer removes it regardless of releaseDatetime. 
  - seatHold object contains seat information and customer information. 
  - Seats can be returned from hold to Available Seats if reserved `indicator` is `false` and `releaseDatetime` is expired. 
  
 ### Ticket  
When a ticket is sold, it will be removed from AvailableSeats and SeatHold and it will be populated in to ticket schema or table. Using Api calls or any other means seller
can requests inventory data on the sold seats for that event. The data from this ticketing service will be used to check sold tickets, inventory, 
reporting, seller website seat mapping update etc. So putting sold tickets in separate class or table or dto helps for separation of concerns and it makes things
simple. 
 - Ticket object contains seat information and customer information. 

 ### TicketServiceImp class
TicketServiceImpl class contains implementation methods for the given home work. The methods in this class calls other service classes like available seats and 
seats hold. Best available seats search method implementation is also under `AvailableSeatsServiceImpl` which is called by `findAndHoldSeats` method in
TicketServiceImpl class during seat hold. I also create dummy available seats in `AvailableSeatsRepositoryImpl` class that will populate five available seats when
the application start
     
 ### TicketServiceImpTest class
This is a unit test for TicketServiceImp class. Junit mockito is used for unit test and checked in eclipse using code coverage plugin. 
  Use `mvn test` to run the test

## Prerequisite

* This project is based on maven with spring boot version 1.5

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
  
  From eclipse, you can right click and run unit test. It should show green bar
  
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
           
          
     
