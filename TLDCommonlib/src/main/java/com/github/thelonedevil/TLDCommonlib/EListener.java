package com.github.thelonedevil.TLDCommonlib;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerLoginEvent;

import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * A Basic Event Listener for Spout
 */
public class EListener implements Listener {
	private Lib plugin;

	public EListener(Lib instance) {
		this.plugin = instance;
	}

    int first = 1;
	String name;
	@EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        name = player.getName();
        if (plugin.logins.get(name)== null){
        	plugin.logins.put(name,first);
        }else if (plugin.logins.get(name)>= 1){
        	int New = (plugin.logins.get(name))+1; 
        	plugin.logins.put(name,New);
		}
	}
}
