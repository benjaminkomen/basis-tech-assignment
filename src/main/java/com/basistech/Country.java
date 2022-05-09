package com.basistech;

import java.util.List;
import java.util.Random;

/**
 * For this example just three random countries were chosen, this can be extended with more countries.
 */
public enum Country {
    CA, // Canada
    US, // United States of America
    MX; // Mexico

    private static final List<Country> VALUES = List.of(values());
    private static final Random RANDOM = new Random();

    public static Country randomCountry() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }
}
