package com.basistech.advertising;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AdvertisingRepository {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Campaign> getCampaigns() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("advertising_campaigns.json")) {

            return mapper.readValue(in, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new AdvertisingCampaignsReadingException("Could not convert JSON list of advertising campaigns to Java objects.", e);
        } catch (IOException e) {
            throw new AdvertisingCampaignsReadingException("Could not read list of advertising campaigns from JSON file.", e);
        }
    }
}
