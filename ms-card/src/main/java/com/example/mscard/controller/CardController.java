package com.example.mscard.controller;
import com.example.mscard.model.ServiceResponse;
import com.example.mscard.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.mscard.model.Card;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import com.example.mscard.service.CardService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/card")
public class CardController {
    @Autowired
    private ICardService iCardService;

    @GetMapping(value = "/List")
    public ResponseEntity<Object>get(){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Card> list = iCardService.findAll();
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        }catch(Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Card card){
        ServiceResponse serviceResponse = new ServiceResponse();
        int result =iCardService.save(card);
        if (result == 1) {

            serviceResponse.setMessage("Item saved with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    //public ResponseEntity<Object> save(@RequestBody Card card){
        //Map<String, Object> map = new HashMap<String, Object>();
       // try{
        //    int result = iCardService.save(card);
        //    ServiceResponse serviceResponse = new ServiceResponse();
        //    return new ResponseEntity<Object>(result, HttpStatus.OK);
       // }catch (Exception e){
       //     map.put("message",e.getMessage());
       //     return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
      //  }}


    @PostMapping("/update")
    public ResponseEntity<ServiceResponse>update(@RequestBody Card card){
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iCardService.update(card);
        if(result==1){
            serviceResponse.setMessage("Item update with success");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse>update(@PathVariable int id){
        ServiceResponse serviceResponse= new ServiceResponse();
        int result = iCardService.deleteById(id);
        if(result==1){
            serviceResponse.setMessage("Item removed with success");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);
    }
}
