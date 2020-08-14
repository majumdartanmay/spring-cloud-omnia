package org.cloud.omnia.server.web;

import Networking.DTO.LogRequestsDTO;
import database.OmniaSearchFilters;
import org.cloud.omnia.server.converters.BaseConverterInterface;
import org.cloud.omnia.server.converters.LogRequestDTOToNetworkEntity;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.cloud.omnia.server.database.specifications.NetworkRequestSpecification;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "${spring.cloud.omnia.server.prefix:}")
@Validated
public class OmniaController {

    //private static Logger logger = Logger.getLogger(OmniaController.class.getName());

    private BasicOmniaQueue basicOmniaQueue;

    @Autowired
    private NetworkRequestRepository networkRequestRepository;

    public OmniaController(BasicOmniaQueue basicOmniaQueue){
        this.basicOmniaQueue = basicOmniaQueue;
    }

    @PostMapping("{profile}/logs/create")
    public ResponseEntity<?> createLog(@PathVariable String profile, @Valid @RequestBody LogRequestsDTO requestsDTO){
        try{

            BaseConverterInterface<LogRequestsDTO, NetworkRequestEntity> converter =
                    new LogRequestDTOToNetworkEntity();

            basicOmniaQueue.addToQueue(converter.convert(requestsDTO));

            return new ResponseEntity<>(true, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logs/fetch")
    public ResponseEntity<?> fetchLogs(@RequestBody OmniaSearchFilters[] filters){
        try{

            Specification<NetworkRequestEntity> dbFilters = new NetworkRequestSpecification(filters);

            //List<NetworkRequestEntity> result = networkRequestRepository.findAll(dbFilters);
            List<NetworkRequestEntity> result = new ArrayList<>();

            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ping")
    public boolean ping(){
        return true;
    }

}
