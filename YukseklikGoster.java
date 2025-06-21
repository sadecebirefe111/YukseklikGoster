package com.ornek.yukseklik;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class YukseklikGoster extends JavaPlugin implements Listener {

    private final HashMap<Player, Integer> oyuncuYukseklikMap = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("Yükseklik Gösterici eklentisi aktif!");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Yükseklik Gösterici eklentisi devre dışı.");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player oyuncu = event.getPlayer();
        Location konum = oyuncu.getLocation();
        int yeniYukseklik = konum.getBlockY();

        if (oyuncuYukseklikMap.containsKey(oyuncu) && oyuncuYukseklikMap.get(oyuncu) == yeniYukseklik) {
            return;
        }

        oyuncuYukseklikMap.put(oyuncu, yeniYukseklik);
        oyuncu.sendActionBar("§e📏 Yüksekliğin: §a" + yeniYukseklik + " §eblok");
    }
}