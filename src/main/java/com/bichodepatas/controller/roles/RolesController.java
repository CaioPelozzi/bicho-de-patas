package com.bichodepatas.controller.roles;

import com.bichodepatas.entities.roles.RolesRequest;
import com.bichodepatas.entities.roles.RolesResponse;
import com.bichodepatas.service.roles.RolesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private RolesService service;

    public RolesController(RolesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RolesResponse> create(@Valid @RequestBody RolesRequest request){
        RolesResponse response = service.create(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesResponse> getById(@PathVariable Long id){

        RolesResponse response = service.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RolesResponse>> getAll(){
        List<RolesResponse> responses = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RolesResponse> update(@PathVariable Long id, @Valid @RequestBody RolesRequest request){
        RolesResponse response = service.update(id, request);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted");
    }
}
