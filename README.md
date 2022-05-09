# Assignment Basis Technologies

Evaluate bid requests against advertising campaigns.

## Instructions

To build the program execute `./gradlew clean build` from your terminal.

To run the program execute `./gradlew run`. Optionally, you can give the total bid requests as an input argument, for
example: `./gradlew run --args=100` will execute the program with 100 bid requests.

## Assumptions

- Java 17 is used, as it makes most sense to use the latest LTS version of Java;
- Since the requirement stated no external libraries except JSON parsing are allowed, no logging library is used, but
  logs are directly printed to stout;
