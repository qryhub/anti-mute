package de.qry.antimute;/*
*      Klasse: AntiMute
*      Erstellt am 08.03.2018 um 21:28
*      © 2018 _qry
*
*/

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import de.qry.antimute.listener.ChatEventListener;
import de.qry.antimute.manager.BlockedWordsManager;
import de.qry.antimute.modules.AntiMuteBlockedWordsCounterModule;
import de.qry.antimute.modules.AntiMuteWordsCounterModule;
import net.labymod.api.LabyModAddon;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

public class AntiMute extends LabyModAddon {

    private static String PREFIX;
    private static AntiMute INSTANCE;
    public boolean enabled;
    private BlockedWordsManager blockedWordsManager;

    static {
        PREFIX = "§7[§aAntiMute§7] ";
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        System.out.println("Enabled AntiMute Addon by _qry");
        blockedWordsManager = new BlockedWordsManager();
        new ChatEventListener();
        new AntiMuteWordsCounterModule();
        new AntiMuteBlockedWordsCounterModule();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void loadConfig() {
        this.enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
        blockedWordsManager.importWords();
        blockedWordsManager.saveToConfig();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    public static AntiMute getInstance(){
        return INSTANCE;
    }

    public BlockedWordsManager getBlockedWordsManager() {
        return blockedWordsManager;
    }

    public static void messagePlayer(final String message){
        LabyMod.getInstance().displayMessageInChat(PREFIX + message);
    }

    public static void blockMessage(String triggerString){
        messagePlayer("Inhalt §c\""
                + triggerString.toUpperCase()
                + "\" "
                + "§7gefunden. Nachricht wurde nicht gesendet.");
    }
}
