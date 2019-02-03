/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.models;


public class convite {
    

private int id;
private String email;
private String data_send;
private String event_date;

    public convite() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_send() {
        return data_send;
    }

    public void setData_send(String data_send) {
        this.data_send = data_send;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    @Override
    public String toString() {
        return "convite{" + "id=" + id + ", email=" + email + ", data_send=" + data_send + ", event_date=" + event_date + '}';
    }



    




}
