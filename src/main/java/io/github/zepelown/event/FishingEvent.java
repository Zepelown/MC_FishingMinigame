package io.github.zepelown.event;

import io.github.zepelown.GameData.FirstGameDataManager;
import io.github.zepelown.inventory.InventoryList;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_ENTITY;
import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;


public class FishingEvent implements Listener {
    private static HashMap<Player, ItemStack> Hooked_fish_list = new HashMap<>();
    @EventHandler
    public void PlayerFishingEvent(PlayerFishEvent e) {
        Player p = e.getPlayer();

        if(e.getState().equals(CAUGHT_FISH) || e.getState().equals(CAUGHT_ENTITY)) {
            Item Hooked_fish_temp = (Item) e.getCaught();
            ItemStack Hooked_fish = Hooked_fish_temp.getItemStack();
            InventoryList il = new InventoryList();
            FirstGameDataManager FDataManager = new FirstGameDataManager();

            FDataManager.set_direction(p, "right");
            FDataManager.set_end_count(p, 0);
            add_Hooked_fish(p, Hooked_fish);
            il.FirstGameInventory.open(p);
            e.setCancelled(true);
        }
    }

    public void add_Hooked_fish(Player p, ItemStack Hooked_fish) {
        Hooked_fish_list.put(p, Hooked_fish);
    }

    public ItemStack get_Hooked_fish(Player p) {
        return Hooked_fish_list.get(p);
    }

    public void remove_Hooked_fish(Player p) {
        Hooked_fish_list.remove(p);
    }

    public void print_Hooked_fish() {
        System.out.println("------잡힌 물고기-------");
        for(Player mapkey : Hooked_fish_list.keySet())
            System.out.println("플레이어 : " + mapkey + " 값 : " + Hooked_fish_list.get(mapkey));
        System.out.println("----------------------");
    }
}
