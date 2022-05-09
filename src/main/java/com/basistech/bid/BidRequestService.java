package com.basistech.bid;

import com.basistech.Country;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class BidRequestService {

    private static final List<String> DIMENSIONS = List.of(
            "100x200",
            "100x300",
            "100x400",
            "200x200",
            "200x300",
            "300x300",
            "300x400",
            "300x400",
            "400x500",
            "400x600",
            "500x600",
            "500x700",
            "600x700",
            "700x700",
            "800x800",
            "800x900"
    );
    private static final List<String> DOMAINS = List.of(
            "apple.com",
            "walmart.com",
            "pepsi.com"
    );
    private static final Random RANDOM = new Random();

    public List<BidRequest> generateRequests(int amountOfRequests) {
        return Stream.iterate(1, x -> x + 1)
                .limit(amountOfRequests)
                .map(this::generateRequest)
                .toList();
    }

    private BidRequest generateRequest(Integer requestId) {
        var pageUrl = generatePageUrl();
        var country = Country.randomCountry();
        var dimensions = generateDimensions();
        return BidRequest.of(requestId, pageUrl, country, dimensions);
    }

    private String generatePageUrl() {
        var randomDomain = DOMAINS.get(RANDOM.nextInt(DOMAINS.size()));
        return String.format("https://%s/ca/store?item=1290", randomDomain);
    }

    private List<String> generateDimensions() {
        var first = DIMENSIONS.get(RANDOM.nextInt(DIMENSIONS.size()));
        var second = DIMENSIONS.get(RANDOM.nextInt(DIMENSIONS.size()));
        return List.of(first, second);
    }
}
