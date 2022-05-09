package com.basistech.bid;

import com.basistech.advertising.Campaign;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BidProcessingService {

    private final List<Campaign> campaigns;

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    public BidProcessingService(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public List<EvaluationResult> processRequests(List<BidRequest> bidRequests) {

        var bidRequestTasks = bidRequests.stream()
                .map(bidRequest -> createTask(bidRequest, campaigns))
                .toList();

        try {
            return executorService.invokeAll(bidRequestTasks).stream()
                    .map(future -> {
                        try {
                            return future.get(5000, TimeUnit.MILLISECONDS);
                        } catch (Exception e) {
                            throw new ProcessInterruptedException("Could not get result of bid request task", e);
                        }
                    })
                    .toList();
        } catch (InterruptedException e) {
            throw new ProcessInterruptedException("Executing bid request tasks was interrupted", e);
        } finally {
            executorService.shutdown();
        }
    }

    private Callable<EvaluationResult> createTask(BidRequest bidRequest, List<Campaign> campaigns) {
        return () -> processRequest(bidRequest, campaigns);
    }

    private static EvaluationResult processRequest(BidRequest bidRequest, List<Campaign> campaigns) {
        var eligibleCampaigns = campaigns.stream()
                .filter(campaign -> isEligible(campaign, bidRequest))
                .map(Campaign::getCampaignId)
                .toList();
        return EvaluationResult.of(bidRequest, eligibleCampaigns);
    }

    private static boolean isEligible(Campaign campaign, BidRequest bidRequest) {
        var countryMatches = campaign.getCountry() == bidRequest.getCountry();
        var domainMatches = bidRequest.getPageURL().contains(campaign.getDomain());
        var dimensionsMatch = campaign.getDimensions().stream()
                .anyMatch(campaignDimension -> bidRequest.getDimensions().contains(campaignDimension));
        return countryMatches && domainMatches && dimensionsMatch;
    }

    public static class ProcessInterruptedException extends RuntimeException {
        public ProcessInterruptedException(String message, Throwable error) {
            super(message, error);
        }
    }
}
