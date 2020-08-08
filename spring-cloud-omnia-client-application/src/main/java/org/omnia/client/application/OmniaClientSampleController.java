package org.omnia.client.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OmniaClientSampleController {
    @GetMapping("test/get")
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
