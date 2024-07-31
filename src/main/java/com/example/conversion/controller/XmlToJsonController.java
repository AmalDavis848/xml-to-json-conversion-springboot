//package com.example.conversion.controller;
//
//import com.example.conversion.service.XmlToJsonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class XmlToJsonController {
//    private final XmlToJsonService xmlToJsonService;
//
//    @Autowired
//    public XmlToJsonController(XmlToJsonService xmlToJsonService) {
//        this.xmlToJsonService = xmlToJsonService;
//    }
//
//    @PostMapping("/convert")
//    public ResponseEntity<?> convertXmlToJson(@RequestBody String xmlData) {
//        try {
//            String jsonResult = xmlToJsonService.convertXmlToJson(xmlData);
//            return ResponseEntity.ok(jsonResult);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting XML to JSON: " + e.getMessage());
//        }
//    }
//}
