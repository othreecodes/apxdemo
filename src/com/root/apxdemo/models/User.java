
package com.root.apxdemo.models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {

    /**
     * This is compulsory for both the database and to access models
     * 
     */
    @DatabaseField(generatedId = true, columnName = "id")
    @SerializedName("id")
    private int id;
    /**
     * Username field
     * 
     */
    @DatabaseField(columnName = "username", canBeNull = false, unique = true)
    @SerializedName("username")
    private String username;
    @DatabaseField(columnName = "password", canBeNull = false, unique = false)
    @SerializedName("password")
    private String password;

    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    /**
     * 
     * @param password
     * @param username
     */
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
    }

    /**
     * Username field
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username field
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *     The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
