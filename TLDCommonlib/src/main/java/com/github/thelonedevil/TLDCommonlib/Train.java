package com.github.thelonedevil.TLDCommonlib;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Train {

	List<UUID> trains = new ArrayList<UUID>();

	public void addCart(UUID cart) {
		if (!trains.contains(cart)) {
			trains.add(cart);
		}
	}

	public void createTrain(UUID cart1, UUID cart2) {
		if (trains == null && !cart1.equals(cart2)) {
			trains.add(cart1);
			trains.add(cart2);
		}
	}

	public List<UUID> getCarts() throws NullPointerException {
		if (!trains.isEmpty()) {
			return trains;
		} else
			throw new NullPointerException();
	}
}
