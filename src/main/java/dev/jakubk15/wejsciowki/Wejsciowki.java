package dev.jakubk15.wejsciowki;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.Lang;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Wejsciowki extends SimplePlugin implements Listener {

	@Override
	public void onPluginStart() {

	}

	@EventHandler
	public void onDimensionChange(final PlayerChangedWorldEvent event) {
		final Set<UUID> uuids_nether = new HashSet<>();
		final Set<UUID> uuids_end = new HashSet<>();
		final Player p = event.getPlayer();
		final World w = p.getWorld();
		final RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

		if (w.getEnvironment().equals(World.Environment.NETHER)) {
			if (!uuids_nether.contains(p.getUniqueId())) {
				if (rsp.getProvider().has(p, 50)) {
					final EconomyResponse r = rsp.getProvider().withdrawPlayer(p, 50);
					if (r.transactionSuccess()) {
						p.sendMessage(Lang.of("MessageNether"));
					}
				}

			}
		} else if (w.getEnvironment().equals(World.Environment.THE_END)) {
			if (!uuids_end.contains(p.getUniqueId())) {
				if (rsp.getProvider().has(p, 100)) {
					final EconomyResponse r = rsp.getProvider().withdrawPlayer(p, 100);
					if (r.transactionSuccess()) {
						p.sendMessage(Lang.of("MessageEnd"));
					}
				}
			}
		}
	}
}
