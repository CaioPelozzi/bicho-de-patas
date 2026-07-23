package com.bichodepatas.controller.users;

import com.bichodepatas.entities.users.UsersRequest;
import com.bichodepatas.entities.users.UsersResponse;
import com.bichodepatas.service.users.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsersResponse> create(@Valid @RequestBody UsersRequest request){
        UsersResponse response = service.create(request);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getById(@PathVariable Long id){
        UsersResponse response = service.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping
    public ResponseEntity<List<UsersResponse>> getAll(){
        List<UsersResponse> responses = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> update(@PathVariable Long id, @RequestBody UsersRequest request){
        UsersResponse response = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted");
    }


}
