package com.basistech;

import com.basistech.advertising.AdvertisingRepository;
import com.basistech.bid.BidProcessingService;
import com.basistech.bid.BidRequestService;
import com.basistech.output.Output;
import com.basistech.output.OutputRepository;

import java.time.Duration;
import java.time.Instant;

public class CampaignBidsMatchingService {

    private final Integer totalBidRequests;

    private final AdvertisingRepository advertisingRepository = new AdvertisingRepository();
    private final BidRequestService bidRequestService = new BidRequestService();

    private final BidProcessingService bidProcessingService = new BidProcessingService();
    private final OutputRepository outputRepository = new OutputRepository();

    public CampaignBidsMatchingService(int totalBidRequests) {
        this.totalBidRequests = totalBidRequests;
    }

    public void start() {
        Instant starts = Instant.now();
        var campaigns = advertisingRepository.getCampaigns();
        System.out.printf("Reading %s advertising campaigns from disk.%n", campaigns.size());

        var bidRequests = bidRequestService.generateRequests(totalBidRequests);

        // TODO: possible make the processing happen concurrently
        var processingResults = bidRequests.stream()
                .map(bidRequest -> bidProcessingService.process(bidRequest, campaigns))
                .toList();
        
        var totalEligibleBids = processingResults.stream()
                .filter(result -> !result.getEligibleCampaignIds().isEmpty())
                .count();

        System.out.printf("From the %s total bid requests processed, there were %s which found an eligible campaign.%n", totalBidRequests, totalEligibleBids);

        Instant ends = Instant.now();
        long totalEvaluationTimeInMilliseconds = Duration.between(starts, ends).toMillis();
        System.out.printf("Processing took %s milliseconds to finish.%n", totalEvaluationTimeInMilliseconds);

        var output = Output.of(campaigns, processingResults, processingResults.size(), totalEvaluationTimeInMilliseconds);

        outputRepository.writeOutput(output);
        System.out.println("Wrote output to file - program finished.");
    }
}
