package com.basistech.output;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;

public class OutputRepository {

    private final ObjectMapper mapper = new ObjectMapper();

    public void writeOutput(Output output) {
        try {
            mapper.writeValue(Paths.get(System.getProperty("user.dir") + "/output.json").toFile(), output);
        } catch (Exception e) {
            throw new RuntimeException("Could not write output file", e);
        }
    }
}
