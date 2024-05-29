package com.example.insurance.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.Objects;

@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email", nullable = true)
    private String email;
    @Lob
    @Column(name = "card", nullable = false)
    private String card;

    public Insurance() {
    }

    public Insurance(int id, String email, String card) {
        this.id = id;
        this.email = email;
        this.card = card;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Insurance id(int id) {
        setId(id);
        return this;
    }

    public Insurance email(String email) {
        setEmail(email);
        return this;
    }

    public Insurance card(String card) {
        setCard(card);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Insurance)) {
            return false;
        }
        Insurance insurance = (Insurance) o;
        return id == insurance.id && Objects.equals(email, insurance.email) && Objects.equals(card, insurance.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, card);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", card='" + getCard() + "'" +
            "}";
    }

    
}
