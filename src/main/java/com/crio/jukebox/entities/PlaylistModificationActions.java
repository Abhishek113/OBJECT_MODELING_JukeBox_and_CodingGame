package com.crio.jukebox.entities;

public enum PlaylistModificationActions {
    
    ADD_SONG("ADD-SONG"), DELETE_SONG("DELETE-SONG");
    private String action_value;
    private PlaylistModificationActions(String value)
    {
        this.action_value = value;
    }
    public static String getValueOfAction(PlaylistModificationActions action){
        return action.action_value;
    }
    public static void main(String[] args) {
        for(PlaylistModificationActions action: PlaylistModificationActions.values())
        {
            System.out.println(action.action_value);
        }
    }
}