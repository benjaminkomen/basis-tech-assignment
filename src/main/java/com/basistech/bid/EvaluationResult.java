package com.basistech.bid;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EvaluationResult {
    @JsonProperty("bid_request")
    private BidRequest bidRequest;
    @JsonProperty("eligible_campaign_ids")
    private List<Integer> eligibleCampaignIds;

    private EvaluationResult(BidRequest bidRequest, List<Integer> eligibleCampaignIds) {
        this.bidRequest = bidRequest;
        this.eligibleCampaignIds = eligibleCampaignIds;
    }

    public static EvaluationResult of(BidRequest bidRequest, List<Integer> eligibleCampaignIds) {
        return new EvaluationResult(bidRequest, eligibleCampaignIds);
    }
}
