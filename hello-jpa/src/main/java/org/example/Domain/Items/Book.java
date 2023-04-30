package org.example.Domain.Items;

import jakarta.persistence.Entity;
import org.example.Domain.Item;

@Entity
public class Book extends Item {

    private String arttist;
    private String etc;

    public String getArttist() {
        return arttist;
    }

    public void setArttist(String arttist) {
        this.arttist = arttist;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
