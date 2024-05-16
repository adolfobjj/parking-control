package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    final ParkingSpotService parkingSpotService;

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingSpotModel save( @RequestBody ParkingSpotModel parkingSpotModel ){
        return parkingSpotRepository.save(parkingSpotModel);
    }
    @GetMapping("/{id}")
    public ParkingSpotModel getParkingSpotById( @PathVariable Long id ){
        return parkingSpotRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Carro não encontrado"));
    }
    @GetMapping("/car/{licensePlateCar}")
    public ResponseEntity<?> getResponsibleByLicensePlate(@PathVariable String licensePlateCar) {
        Optional<ParkingSpotModel> parkingSpot = parkingSpotService.findByLicensePlateCar(licensePlateCar);
        if (parkingSpot.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("responsibleName", parkingSpot.get().getResponsibleName());
            response.put("apartment", parkingSpot.get().getApartment());
            response.put("block", parkingSpot.get().getBlock());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with license plate " + licensePlateCar + " not found.");
        }
    }
    @GetMapping
    public List<ParkingSpotModel> find( ParkingSpotModel filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return parkingSpotRepository.findAll(example);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Long id ){
        parkingSpotRepository.findById(id)
                .map( cliente -> {
                    parkingSpotRepository.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody ParkingSpotModel parkingSpotModel ){
        parkingSpotRepository
                .findById(id.longValue())
                .map( clienteExistente -> {
                    parkingSpotModel.setId(clienteExistente.getId());
                    parkingSpotRepository.save(parkingSpotModel);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }






}
