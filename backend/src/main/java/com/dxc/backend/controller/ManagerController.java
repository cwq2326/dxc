package com.dxc.backend.controller;

import com.dxc.backend.dto.DocumentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin
public class ManagerController {

  @GetMapping("document")
  public ResponseEntity<DocumentResponseDto> document() {
    return new ResponseEntity<>(
        new DocumentResponseDto("This is a restricted content sent from the Java backend server"),
        HttpStatus.OK
    );
  }

}
