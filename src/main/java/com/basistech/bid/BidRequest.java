package com.basistech.bid;

import com.basistech.Country;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BidRequest {

    @JsonProperty("request_id")
    private Integer requestId;
    @JsonProperty("page_url")
    private String pageURL;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("dimensions")
    private List<String> dimensions;

    private BidRequest(Integer requestId, String pageURL, Country country, List<String> dimensions) {
        this.requestId = requestId;
        this.pageURL = pageURL;
        this.country = country;
        this.dimensions = dimensions;
    }

    public static BidRequest of(Integer requestId, String pageURL, Country country, List<String> dimensions) {
        return new BidRequest(requestId, pageURL, country, dimensions);
    }
}
