package com.basistech;

import com.basistech.advertising.AdvertisingRepository;
import com.basistech.bid.BidRequestService;
import com.basistech.output.Output;
import com.basistech.output.OutputRepository;

import java.time.Duration;
import java.time.Instant;

// TODO: come up with a better name
public class CampaignBidsMatchingService {

    private final AdvertisingRepository advertisingRepository = new AdvertisingRepository();
    private final BidRequestService bidRequestService = new BidRequestService();
    private final OutputRepository outputRepository = new OutputRepository();

    public void start() {
        Instant starts = Instant.now();
        var campaigns = advertisingRepository.getCampaigns();
        System.out.printf("Reading %s advertising campaigns from disk.%n", campaigns.size());

        var bidRequests = bidRequestService.generateRequests(5); // TODO: increase amount of requests

        // TODO: process bid requests here

        Instant ends = Instant.now();
        long totalEvaluationTimeInMilliseconds = Duration.between(starts, ends).toMillis();
        System.out.printf("Program took %s milliseconds to finish.", totalEvaluationTimeInMilliseconds);

        // TODO: compute total bid requests processed
        var output = Output.of(campaigns, -1, totalEvaluationTimeInMilliseconds);

        outputRepository.writeOutput(output);
        System.out.println("Wrote output to file - program finished.");
    }
}
