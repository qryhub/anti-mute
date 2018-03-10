package de.qry.antimute.modules;/*
*      Klasse: AntiMuteBlockedWordsCounterModule
*      Erstellt am 09.03.2018 um 13:30
*      © 2018 _qry
*
*/


import de.qry.antimute.AntiMute;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class AntiMuteBlockedWordsCounterModule extends SimpleModule {

    public AntiMuteBlockedWordsCounterModule(){
        AntiMute.getInstance().getApi().registerModule(this);
    }

    @Override
    public String getDisplayName() {
        return "Blockierte Nachrichten";
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(AntiMute.getInstance().getBlockedWordsManager().getBlocked_amount());
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData( Material.BARRIER );
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Blockierte Nachrichten";
    }

    @Override
    public String getDescription() {
        return "Zeigt an, wie viele Nachrichten blockiert wurden.";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
