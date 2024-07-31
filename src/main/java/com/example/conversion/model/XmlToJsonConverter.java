//package com.example.conversion.model;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

public class XmlToJsonConverter {

    public static void main(String[] args) {
        String inputFile = "input.xml";
        String outputFile = "output.json";

        try {
            convertXmlToJson(inputFile, outputFile);
            System.out.println("Conversion completed successfully.");
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void convertXmlToJson(String inputFile, String outputFile) throws IOException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream(inputFile);
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);

        JsonFactory jsonFactory = new MappingJsonFactory();
        OutputStream outputStream = new FileOutputStream(outputFile);
        JsonGenerator generator = jsonFactory.createGenerator(outputStream);

        generator.writeStartObject();
        generator.writeArrayFieldStart("Recordings");

        String currentElement = null;
        Recording recording = null;

        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamReader.START_ELEMENT:
                    currentElement = reader.getLocalName();
                    if ("RECORDING".equals(currentElement)) {
                        recording = new Recording();
                    }
                    break;
                case XMLStreamReader.CHARACTERS:
                    String text = reader.getText().trim();
                    if (!text.isEmpty()) {
                        if ("RECORDING-TITLE-COLLECTING-SOCIETY".equals(currentElement)) {
                            recording.setTitle(text.toUpperCase());
                        } else if ("MAIN-ARTIST-NAME-COLLECTING-SOCIETY".equals(currentElement)) {
                            recording.setArtist(text.toUpperCase());
                        }
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    currentElement = reader.getLocalName();
                    if ("RECORDING".equals(currentElement)) {
                        generator.writeStartObject();
                        generator.writeStringField("Title", recording.getTitle());
                        generator.writeArrayFieldStart("MainArtists");
                        generator.writeStartObject();
                        generator.writeStringField("Artist", recording.getArtist());
                        generator.writeEndObject();
                        generator.writeEndArray();
                        generator.writeEndObject();
                    }
                    break;
            }
        }

        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        outputStream.close();
        reader.close();
        inputStream.close();
    }

    static class Recording {
        private String title;
        private String artist;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }
    }
}
