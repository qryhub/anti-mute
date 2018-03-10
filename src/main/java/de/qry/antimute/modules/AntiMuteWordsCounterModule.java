package de.qry.antimute.modules;/*
*      Klasse: AntiMuteWordsCounterModule
*      Erstellt am 09.03.2018 um 12:35
*      © 2018 _qry
*
*/

import de.qry.antimute.AntiMute;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class AntiMuteWordsCounterModule extends SimpleModule {

    public AntiMuteWordsCounterModule(){
        AntiMute.getInstance().getApi().registerModule(this);
    }

    @Override
    public String getDisplayName() {
        return "Geladene Nachrichten";
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(AntiMute.getInstance().getBlockedWordsManager().getBlocked().size());
    }

    @Override
    public String getDefaultValue() {
        return "?";
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
        return "Geladene Nachrichten";
    }

    @Override
    public String getDescription() {
        return "Momentan sind "
                + String.valueOf(AntiMute.getInstance().getBlockedWordsManager().getBlocked().size())
                + " Nachrichten geladen.";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
