package org.cloud.omnia.server.web;

import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.cloud.omnia.server.web.DTO.LogRequestsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "${spring.cloud.omnia.server.prefix:}")
@Validated
public class OmniaController {

    private static Logger logger = Logger.getLogger(OmniaController.class.getName());

    private NetworkRequestRepository networkRequestRepository;

    public OmniaController(NetworkRequestRepository networkRequestRepository){
        this.networkRequestRepository = networkRequestRepository;
    }

    @PostMapping("/createLog")
    public ResponseEntity<?> createLog(@Valid @RequestBody LogRequestsDTO requestsDTO){
        try{
            NetworkRequestEntity networkRequestEntity = requestsDTO.getNetworkRequestEntity();
            networkRequestRepository.save(networkRequestEntity);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public boolean ping(){
        return true;
    }

}
