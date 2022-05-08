# Assignment Basis Technologies

Evaluate bid requests against advertising campaigns.

## Assumptions

- Java 17 is used, as it makes most sense to use the latest LTS version of Java;
- Since the requirement stated no external libraries except JSON parsing are allowed, no logging library is used, but
  logs are directly printed to stout;
- 

## Procedure

- Manually create mock input of:
    - several advertising campaigns

- start a timer
- Randomly generate several bid requests

- Create a processor / worker which:
    - pulls a bid request
    - tries to match it with an advertising campaign
    - gives back the result
- end a timer
- collect all the data in a Java file
- write the Java result file to a JSON file

Example:

- there are several campaigns, e.g.:
    - 1, Canada, apple.com, [300x250, 600x200]
    - 2, USA, walmart.com, [200x100, 400x200]
    - 3, Mexico, pepsi.com, [300x250, 400x200]

this is static data, it is retrieved from a repository which fetches it from a JSON file

The processor gets a stream of bid requests and tries to match them with a campaign.

There can be multiple concurrent processors.
