package com.walmart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TicketingController {

  @ApiOperation(value = "Ticket ", notes = "Ticket .....")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Fields are with validation errors")})
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/Ticket", method = RequestMethod.GET)
  public String home() {
    return "Hello World!";
  }
}
