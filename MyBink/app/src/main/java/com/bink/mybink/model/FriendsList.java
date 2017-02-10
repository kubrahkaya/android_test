package com.bink.mybink.model;

import com.gvillani.pinnedlist.GroupListWrapper;

/**
 * Created by kub on 10/02/2017.
 */

public class FriendsList implements GroupListWrapper.Selector{
    private String name;
    private String surname;
    private Integer photo;

    public FriendsList(String name, String surname, Integer photo) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "FriendsList{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", photo=" + photo +
                '}';
    }

    @Override
    public String select() {
        return getName()+getSurname();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
