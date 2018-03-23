package de.qry.antimute.manager;/*
*      Klasse: BlockedWordsManager
*      Erstellt am 08.03.2018 um 22:24
*      © 2018 _qry
*
*/

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import de.qry.antimute.AntiMute;

import java.util.ArrayList;
import java.util.List;

public class BlockedWordsManager {

    private List<String> blocked;
    private int blocked_amount = 0;

    public BlockedWordsManager(){
        blocked = new ArrayList<String>(); // schema: abc_def
    }

    public void importWords(){
        blocked.clear();
        AntiMute instance = AntiMute.getInstance();
        try{
            JsonArray blockedArray = instance.getConfig().getAsJsonArray("blocked");
        }catch (NullPointerException e){
            createConfig();
        }
        JsonArray blockedArray = instance.getConfig().getAsJsonArray("blocked");
        for(JsonElement el : blockedArray){
            String word = el.getAsString().toLowerCase().trim().replaceAll(" ", "_");
            if(!blocked.contains(word)) blocked.add(word);
        }
    }

    public void reloadWords(){
        AntiMute.getInstance().loadConfig();
        AntiMute.getInstance().saveConfig();
        importWords();
    }

    public void addSeq(String sequence){
        if(!blocked.contains(sequence.toLowerCase().trim().replaceAll(" ", "_"))){
            blocked.add(sequence.toLowerCase().trim().replaceAll(" ", "_"));
            saveToConfig();
        }
    }

    public void removeSeq(String sequence){
        if(blocked.contains(sequence.toLowerCase().trim().replaceAll(" ", "_"))){
            blocked.remove(sequence.toLowerCase().trim().replaceAll(" ", "_"));
            saveToConfig();
        }
    }

    public void createConfig(){
        AntiMute instance = AntiMute.getInstance();
        JsonArray array = new JsonArray();
        instance.getConfig().add("blocked", array);
        instance.saveConfig();
    }

    public void saveToConfig(){
        AntiMute instance = AntiMute.getInstance();
        JsonArray array = new JsonArray();
        for(String s : blocked){
            array.add(new JsonPrimitive(s));
        }
        instance.getConfig().add("blocked", array);
        instance.saveConfig();
    }

    public String isBlocked(final String seq){
        for (String word : getBlocked()) {
            if (seq.toLowerCase().trim().replaceAll(" ", "").contains(word.replaceAll("_", ""))) {
                return word;
            }
        }
        return null;
    }

    public List<String> getBlocked() {
        return blocked;
    }

    public int getBlocked_amount() {
        return blocked_amount;
    }

    public void setBlocked_amount(int blocked_amount) {
        this.blocked_amount = blocked_amount;
    }
}

