package comdbbyerik.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ControllerUserSession {
    

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
    
        
        return entity;
    }
    
    
}
