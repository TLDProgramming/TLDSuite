package com.github.thelonedevil.TLDFactions.Protection;

import java.util.ArrayList;
import java.util.Collection;

import org.spout.api.geo.Protection;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
import org.spout.api.plugin.services.ProtectionService;

public class service extends ProtectionService {
	final ArrayList<Protection> protections = new ArrayList<Protection>();

	@Override
	public Protection getProtection(String name) {
		Protection p = null;
		for (Protection registered : protections) {
			if (registered.getName().equals(name)) {
				p = registered;
				break;
			}
		}
		return p;
	}

	@Override
	public Collection<Protection> getAllProtections(World world) {
		ArrayList<Protection> worldProtections = new ArrayList<Protection>();

		for (Protection p : protections) {
			if (p.getWorld().equals(world)) {
				worldProtections.add(p);
			}
		}

		return worldProtections;
	}

	@Override
	public Collection<Protection> getAllProtections(Point point) {
		ArrayList<Protection> pointProtections = new ArrayList<Protection>();

		for (Protection p : protections) {
			if (p.contains(point)) {
				pointProtections.add(p);
			}
		}

		return pointProtections;
	}

	@Override
	public Collection<Protection> getAllProtections() {
		return protections;
	}

	public void addProtection(Protection protection) {
		if (!protections.contains(protection)) {
			protections.add(protection);
		}
	}
}
