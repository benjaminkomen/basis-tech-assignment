package com.basistech.output;

import com.basistech.advertising.Campaign;
import com.basistech.bid.EvaluationResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Output {

    @JsonProperty("campaigns")
    private List<Campaign> campaigns;
    @JsonProperty("evaluation_results")
    private List<EvaluationResult> evaluationResults;
    @JsonProperty("total_bid_requests_processed")
    private Integer totalBidRequestsProcessed;
    @JsonProperty("total_evaluation_time_in_milliseconds")
    private Long totalEvaluationTimeInMilliseconds;

    private Output(
            List<Campaign> campaigns,
            List<EvaluationResult> evaluationResults,
            Integer totalBidRequestsProcessed,
            Long totalEvaluationTimeInMilliseconds) {
        this.campaigns = campaigns;
        this.evaluationResults = evaluationResults;
        this.totalBidRequestsProcessed = totalBidRequestsProcessed;
        this.totalEvaluationTimeInMilliseconds = totalEvaluationTimeInMilliseconds;
    }

    public static Output of(
            List<Campaign> campaigns,
            List<EvaluationResult> evaluationResults,
            Integer totalBidRequestsProcessed,
            Long totalEvaluationTimeInMilliseconds) {
        return new Output(campaigns, evaluationResults, totalBidRequestsProcessed, totalEvaluationTimeInMilliseconds);
    }
}
