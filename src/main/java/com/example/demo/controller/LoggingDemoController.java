package com.example.demo.controller;

import com.example.demo.response.InternalCode;
import com.example.demo.response.MyApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class LoggingDemoController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDemoController.class);

    @GetMapping("200")
    public ResponseEntity<MyApiResponse> standardTransactionOk() {
        System.out.println("This is big information about details of transaction");
        System.out.println("Standard short info about transaction");
        return new ResponseEntity<>(new MyApiResponse("OK", InternalCode.SUCCESS), HttpStatus.OK);
    }

    @GetMapping("400")
    public ResponseEntity<MyApiResponse> standardTransactionClientErr() {
        System.out.println("This is actual error with details logged");
        System.out.println("This is info about error happened with short message");
        return new ResponseEntity<>(new MyApiResponse("Something wrong with request", InternalCode.CLIENT_ERROR), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("500")    public ResponseEntity<MyApiResponse> standardTransactionServerErr() {
        System.out.println("This is actual error with details logged");
        System.out.println("This is info about error happened with short message");
        return new ResponseEntity<>(new MyApiResponse("Something wrong with server", InternalCode.SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
