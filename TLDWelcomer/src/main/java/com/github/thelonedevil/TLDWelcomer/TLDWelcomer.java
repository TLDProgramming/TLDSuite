package com.github.thelonedevil.TLDWelcomer;

import org.spout.api.plugin.Plugin;


public class TLDWelcomer extends Plugin {
	private static TLDWelcomer instance;
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
	
	private static void setInstance(TLDWelcomer plugin) {
		instance = plugin;
	}

	public static TLDWelcomer getInstance() {
		return instance;
	}

}