package com.github.thelonedevil.TLDCommonlib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class TrainsMap {
	HashMap<Integer, Train> trains = new HashMap<Integer, Train>();

	public void put(Train value) {
		int key = trains.size() + 1;
		trains.put(key, value);
	}

	public void replace(Integer key, Train value) {
		trains.put(key, value);
	}

	public Train get(Integer key) {
		Train train = trains.get(key);
		return train;
	}

	public int size() {
		int size = trains.size();
		return size;

	}

	public Collection<Train> values() {
		Collection<Train> values = trains.values();
		return values;
	}

	public Set<Integer> keySet() {
		Set<Integer> keys = trains.keySet();
		return keys;
	}

	public Boolean isEmpty() {
		if (!trains.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void remove(Integer key) {
		// int size = size();
		// if (size == key.intValue()) {
		trains.remove(key);
		/*
		 * } else { trains.remove(key); int key1 = key.intValue() + 1; int key2
		 * = key.intValue(); while (trains.get(key1) != null) { Train train =
		 * trains.get(key1); trains.put(key2, train); key2++; key1++; }
		 * if(trains.get(size).equals(trains.get(size-1))){ trains.remove(size);
		 * } }
		 */
	}
}
