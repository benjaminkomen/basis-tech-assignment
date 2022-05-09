package com.basistech.bid;

import com.basistech.advertising.Campaign;

import java.util.List;

public class BidProcessingService {

    public EvaluationResult process(BidRequest bidRequest, List<Campaign> campaigns) {
        var eligibleCampaigns = campaigns.stream()
                .filter(campaign -> isEligible(campaign, bidRequest))
                .map(Campaign::getCampaignId)
                .toList();
        return EvaluationResult.of(bidRequest, eligibleCampaigns);
    }

    private boolean isEligible(Campaign campaign, BidRequest bidRequest) {
        var countryMatches = campaign.getCountry() == bidRequest.getCountry();
        var domainMatches = bidRequest.getPageURL().contains(campaign.getDomain());
        var dimensionsMatch = campaign.getDimensions().stream()
                .anyMatch(campaignDimension -> bidRequest.getDimensions().contains(campaignDimension));
        return countryMatches && domainMatches && dimensionsMatch;
    }
}
