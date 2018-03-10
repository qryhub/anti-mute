package de.qry.antimute.listener;/*
*      Klasse: ChatEventListener
*      Erstellt am 08.03.2018 um 22:09
*      © 2018 _qry
*
*/

import de.qry.antimute.AntiMute;
import net.labymod.api.events.MessageSendEvent;

import java.util.List;

public class ChatEventListener implements MessageSendEvent {

    public ChatEventListener(){
        AntiMute.getInstance().getApi().getEventManager().register(this);
    }

    @Override
    public boolean onSend(String message) {

        if(!AntiMute.getInstance().enabled) return false;

        if(message.startsWith("-")){

            final List<String> blocked = AntiMute.getInstance().getBlockedWordsManager().getBlocked();

            if(message.split(" ").length == 1 && message.split(" ")[0].equalsIgnoreCase("-rl")){
                AntiMute.getInstance().getBlockedWordsManager().reloadWords();
                AntiMute.messagePlayer("Wörter §aneu geladen§7.");
            }else

            if(message.split(" ").length == 2 && message.split(" ")[0].equalsIgnoreCase("-add")){

                final String word = message.split(" ")[1];

                if(blocked.contains(word.toLowerCase().trim().replaceAll(" ", "_"))){
                    AntiMute.messagePlayer("Wort bereits vorhanden. §a" + message.split(" ")[1].toUpperCase());
                    return true;
                }
                AntiMute.getInstance().getBlockedWordsManager().addSeq(message.split(" ")[1]);
                AntiMute.messagePlayer("Wort §ahinzugefügt§7. §a" + message.split(" ")[1].toUpperCase());
            }else

            if(message.split(" ").length == 2 && message.split(" ")[0].equalsIgnoreCase("-remove")){

                final String word = message.split(" ")[1];

                if(!blocked.contains(word.toLowerCase().trim().replaceAll(" ", "_"))){
                    AntiMute.messagePlayer("Wort nicht vorhanden. §c" + message.split(" ")[1].toUpperCase());
                    return true;
                }
                AntiMute.getInstance().getBlockedWordsManager().removeSeq(message.split(" ")[1]);
                AntiMute.messagePlayer("Wort §centfernt§7. §c" + message.split(" ")[1].toUpperCase());
            }

            else{
                AntiMute.messagePlayer("§6Hilfeseite §aAntiMute §7by _qry");
                AntiMute.messagePlayer("(Format für Nachrichten: DIES_IST_EIN_BEISPIEL)");
                AntiMute.messagePlayer("-rl | Lädt/importiert die Nachrichten neu.");
                AntiMute.messagePlayer("-add <Nachricht> | Fügt eine Nachricht hinzu.");
                AntiMute.messagePlayer("-remove <Nachricht> | Entfernt eine Nachricht.");
            }
            return true;
        }



        if(AntiMute.getInstance().getBlockedWordsManager().isBlocked(message) != null){
            AntiMute.blockMessage(AntiMute.getInstance().getBlockedWordsManager().isBlocked(message));
            AntiMute.getInstance().getBlockedWordsManager()
                    .setBlocked_amount(AntiMute.getInstance().getBlockedWordsManager().getBlocked_amount()+1);
            return true;
        }
        return false;
    }
}
