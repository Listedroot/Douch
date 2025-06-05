package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class GeoIPBlocker {
    private final Set<String> blockedCountries = new HashSet<>();

    public void blockCountry(String countryCode) {
        blockedCountries.add(countryCode.toUpperCase());
    }

    public void unblockCountry(String countryCode) {
        blockedCountries.remove(countryCode.toUpperCase());
    }

    public boolean isBlocked(String countryCode) {
        return blockedCountries.contains(countryCode.toUpperCase());
    }
}
