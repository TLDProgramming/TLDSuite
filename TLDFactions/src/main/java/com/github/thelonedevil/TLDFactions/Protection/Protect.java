package com.github.thelonedevil.TLDFactions.Protection;

import org.spout.api.geo.Protection;
import org.spout.api.geo.World;
import org.spout.api.geo.discrete.Point;
import org.spout.math.vector.Vector3;

public class Protect extends Protection {

	private final Vector3 center;
	private final int radius;

	public Protect(String name, World world, Vector3 center, int radius) {
		super(name, world);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean contains(Point point) {
		return center.distanceSquared(point) <= radius * radius;
	}

}
