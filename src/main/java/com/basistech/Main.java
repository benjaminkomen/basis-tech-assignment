package com.basistech;

public class Main {

    private static final int DEFAULT_TOTAL_BID_REQUESTS = 100_000;

    public static void main(String[] args) {
        var totalBidRequests = DEFAULT_TOTAL_BID_REQUESTS;
        if (args.length == 1) {
            totalBidRequests = Integer.parseInt(args[0]);
        }
        var bidService = new CampaignBidsMatchingService(totalBidRequests);
        bidService.start();
    }
}
