package com.basistech.advertising;

import com.basistech.Country;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Campaign {

    @JsonProperty("campaign_id")
    private Integer campaignId;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("dimensions")
    private List<String> dimensions;
}
