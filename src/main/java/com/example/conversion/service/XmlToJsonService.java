//package com.example.conversion.service;
//
//import com.example.conversion.model.Recording;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.MappingJsonFactory;
//import org.springframework.stereotype.Service;
//
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamConstants;
//import javax.xml.stream.XMLStreamReader;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//@Service
//public class XmlToJsonService {
//
//    public String convertXmlToJson(String xmlData) throws Exception {
//        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
//        InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());
//        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        JsonFactory jsonFactory = new MappingJsonFactory();
//        JsonGenerator generator = jsonFactory.createGenerator(outputStream);
//
//        generator.writeStartObject();
//        generator.writeArrayFieldStart("Recordings");
//
//        String currentElement = null;
//        Recording recording = null;
//
//        while (reader.hasNext()) {
//            int event = reader.next();
//            switch (event) {
//                case XMLStreamConstants.START_ELEMENT:
//                    currentElement = reader.getLocalName();
//                    if ("RECORDING".equals(currentElement)) {
//                        recording = new Recording();
//                    }
//                    break;
//                case XMLStreamConstants.CHARACTERS:
//                    String text = reader.getText().trim();
//                    if (!text.isEmpty()) {
//                        if ("RECORDING-TITLE-COLLECTING-SOCIETY".equals(currentElement)) {
//                            recording.setTitle(text.toUpperCase());
//                        } else if ("MAIN-ARTIST-NAME-COLLECTING-SOCIETY".equals(currentElement)) {
//                            recording.setArtist(text.toUpperCase());
//                        }
//                    }
//                    break;
//                case XMLStreamConstants.END_ELEMENT:
//                    currentElement = reader.getLocalName();
//                    if ("RECORDING".equals(currentElement)) {
//                        generator.writeStartObject();
//                        generator.writeStringField("Title", recording.getTitle());
//                        generator.writeStringField("Artist", recording.getArtist());
//                        generator.writeEndObject();
//                    }
//                    break;
//            }
//        }
//
//        generator.writeEndArray();
//        generator.writeEndObject();
//
//        generator.close();
//        outputStream.close();
//        reader.close();
//        inputStream.close();
//
//        return outputStream.toString();
//    }
//}
