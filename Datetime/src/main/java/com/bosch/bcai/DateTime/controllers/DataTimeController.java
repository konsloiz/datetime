package com.bosch.bcai.DateTime.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@RestController
@Api(value = "API to get local date and time in two different formats",
        description = "This API provides the capability to get the local date and time in a standard ISO and a simple format")
public class DataTimeController {




    @ApiOperation(value = "Get local date and time in a standard format ", response = Iterable.class, tags = "ISO")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!")})
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public String now() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        return String.valueOf(now);
    }

    @ApiOperation(value = "Get local date and time in a simpler than the standard format ", response = Iterable.class, tags = "Simple")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!")})
    @RequestMapping(value = "/now/simple", method = RequestMethod.GET)
    public String now_simple() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String simpleDateTime = now.format(formatter);
        return simpleDateTime;
    }
}

