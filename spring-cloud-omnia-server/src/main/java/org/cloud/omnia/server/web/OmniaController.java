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
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class to exchange all the API related data.
 * Including writing and reading API logs.
 * @author Tanmay Majumdar
 */
@Controller
@RequestMapping(path = "${spring.cloud.omnia.server.prefix:}")
@Validated
public class OmniaController {

    /**
     * Used to enqueue the data.
     */
    private BasicOmniaQueue basicOmniaQueue;

    /**
     * Repository to fetch the logs.
     */
    @Autowired
    private NetworkRequestRepository networkRequestRepository;

    /**
     * Gets the queue in which the logs are supposed to be pushed.
     * @param suppliedQueue This will be used to enqueue the logs.
     */
    public OmniaController(final BasicOmniaQueue suppliedQueue) {
        this.basicOmniaQueue = suppliedQueue;
    }

    /**
     * API to store logs.
     * @param requestsDTO the API data.
     * @return the result of the request to mark the success of the API.
     */
    @PostMapping("/logs/create")
    @ResponseBody
    public ResponseEntity<?> createLog(
            @Valid @RequestBody final LogRequestsDTO requestsDTO) {
        try {
            BaseConverterInterface<LogRequestsDTO, NetworkRequestEntity>
            converter = new LogRequestDTOToNetworkEntity();
            basicOmniaQueue.addToQueue(converter.convert(requestsDTO));

            return new ResponseEntity<>(true, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String status(HttpServletRequest request, Map<String, Object> model) {
        populateBase( model);
        return "omnia/status";
    }

    protected void populateBase(Map<String, Object> model) {
        model.put("basePath", "/");
        model.put("authorName", "Tanmay Majumdar");
        model.put("time", new Date().toString());
    }

    /**
     * API to fetch the logs.
     * @param filters All the DB filters for the necessary filters.
     * @return The list of logs according to the filters.
     */
    @PostMapping("/logs/fetch")
    @ResponseBody
    public ResponseEntity<?> fetchLogs(
            @RequestBody final OmniaSearchFilters[] filters) {
        try {
            Specification<NetworkRequestEntity> dbFilters =
                    new NetworkRequestSpecification(filters);
            List<NetworkRequestEntity> result =
                    networkRequestRepository.findAll(dbFilters);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * API for health check of the server.
     * @return boolean
     */
    @GetMapping("/ping")
    @ResponseBody
    public boolean ping() {
        return true;
    }

}
