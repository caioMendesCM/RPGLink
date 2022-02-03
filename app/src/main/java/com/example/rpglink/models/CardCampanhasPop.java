package com.example.rpglink.models;

public class CardCampanhasPop {

    private String rpgName, campName, mestreName, friendName, playerNun;
    private int rpgPic;
    private boolean isMeASame;

    public CardCampanhasPop() {
    }

    public String getRpgName() {
        return rpgName;
    }

    public void setRpgName(String name) {

        this.rpgName = name;
    }

    public String getCampanhaName() {
        return campName;
    }

    public void setCampanhaName(String name) {
        this.campName = name;
    }

    public String getMestreName() {
        return mestreName;
    }

    public void setMestreName(String name) {

        String frase = name + " " + "é o mestre dessa campanha";
        this.mestreName = frase;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String name) {
        String frase = name + " " + "está jogando essa campanha";
        this.friendName = frase;
    }

    public String getPlayerNun() {
        return playerNun;
    }

    public void setPlayerNun(String name) {
        this.playerNun = name;
    }

    public int getRpgPic() {
        return rpgPic;
    }

    public void setRpgPic(int i) {
        this.rpgPic = i;
    }

    public boolean isMeASame() {
        if(mestreName == friendName){
            isMeASame = true;
        }
        return isMeASame;
    }
}
