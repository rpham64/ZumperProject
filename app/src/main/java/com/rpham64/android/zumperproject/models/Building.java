package com.rpham64.android.zumperproject.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rpham on 4/20/2017.
 */

public class Building extends Property {

    private HashSet<Listing> units;

    public int minPrice() {

        if (units.size() == 0) return 0;

        int minPrice = Integer.MAX_VALUE;

        Iterator<Listing> iterator = units.iterator();

        while (iterator.hasNext()) {
            Listing listing = iterator.next();
            minPrice = Math.min(minPrice, listing.price);
        }

        return minPrice;
    }

    public int maxPrice() {

        int maxPrice = Integer.MIN_VALUE;

        Iterator<Listing> iterator = units.iterator();

        while (iterator.hasNext()) {
            Listing listing = iterator.next();
            maxPrice = Math.max(maxPrice, listing.price);
        }

        return maxPrice == Integer.MIN_VALUE ? 0 : maxPrice;
    }
}
