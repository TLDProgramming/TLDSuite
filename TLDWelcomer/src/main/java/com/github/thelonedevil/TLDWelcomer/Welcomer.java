package com.github.thelonedevil.TLDWelcomer;

import org.spout.api.plugin.Plugin;


public class Welcomer extends Plugin {
	private static Welcomer instance;
	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		getEngine().getEventManager().registerEvents(new EListener(this), this);
	}

	@Override
	public void onDisable() {
	}
	
	private static void setInstance(Welcomer plugin) {
		instance = plugin;
	}

	public static Welcomer getInstance() {
		return instance;
	}

}