# Ticketing Service
__contact__: Abereham.wodajie@gmail.com

# Assumptions

# Design Explanations

   ### Event: 
Facilitate process of keeping event details up-to-date for all available events for sellers and consumer. Seller sale tickets based on event type.      Each event have unique event name eventId. In order to ensure that event information displayed on their various web sites (for event discovery), we need to implement event retrieve and update methods. In data base we can have event table/schema.  We can also tie events with season. For this project event methods are not implemented
    
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
    
  ### best available seats. 
From a search perspective, Ticket buyers not only want multiple seats, but they want the best ones, as well that means that any “Best Available” search algorithm will be searching the exact same set of seats (section/row) for all concurrent requests.  so we need to develop very efficient algorithm that looks throw the available seats. Seats can be included as part of best available seat based on different criteria. Seller has a few options to play with in order to find the best available seats for the customer. These options can be

 	  - Price levels or range 
      - section of the venue
	  - different ticket types
      - 	Seats on promotions 
	  - Best seats with low price
	  - offer 
	  - single seats may be left open can also be included as part of the best available 
   
   * For this project, “Best Available” search algorithm is not implemented. I create simple method that return best available seat based on seat number range. Implementation of Best Available seats search algorithm need requirement.  
  
 
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

## Prerequisite

* Maven
	* Check if you have maven installed: `mvn --version`
* IDE
	* Either IntelliJ or Eclipse

*clone or download
  * git clone https://github.com/Abrishges/ticketing.git
  
#### To run the project, Navigate to project directory, ticketing  using terminal or cmd, and use the following cmd

  `mvn spring-boot:run`

#### To test calls , use swagger page in your favorite browser 
* URL
    * http://localhost:8080/swagger-ui.html#/
    
* ticketing service urls. 
     * http://localhost:8080/swagger-ui.html#/
          
* using curl to extract information

#RESTful Web Services
  
  ![](/doc/swagger/release-pipeline.png) 