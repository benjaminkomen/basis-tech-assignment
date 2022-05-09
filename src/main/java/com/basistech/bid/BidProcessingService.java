package com.basistech.bid;

import java.util.Collections;

public class BidProcessingService {

    // TODO: implement this method
    public EvaluationResult process(BidRequest bidRequest) {
        return EvaluationResult.of(bidRequest, Collections.emptyList());
    }
}
