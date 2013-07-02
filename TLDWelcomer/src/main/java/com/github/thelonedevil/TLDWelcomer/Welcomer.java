package com.github.thelonedevil.TLDWelcomer;

import org.spout.api.UnsafeMethod;
import org.spout.api.plugin.Plugin;
import org.spout.api.plugin.PluginLogger;

import com.github.thelonedevil.TLDCommonlib.Lib;

public class Welcomer extends Plugin {
	private Lib lib;
	
	@Override
	public void onLoad() {
		((PluginLogger) getLogger()).setTag("[TLDWelcomer]");
		getLogger().info(lib.logged + "Loaded correctly");
	}

	@Override
	public void onReload() {
		getLogger().info(lib.logged + "Reloaded");
	}

	@Override
	@UnsafeMethod
	public void onEnable() {
		getLogger().info(lib.logged + "Enabled");
		getEngine().getEventManager().registerEvents(new EListener(this), this);
	}

	@Override
	@UnsafeMethod
	public void onDisable() {
		getLogger().info(lib.logged + "Disabled");
	}

}