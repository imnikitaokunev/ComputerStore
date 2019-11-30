package com.nikitkasss.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="abstract_user_id")
@Table(name="buyer")
public class Buyer extends AbstractUser{

    @Column(name="buyer_phone")
    private String phone;

    @Column(name="buyer_email")
    private String email;

    @OneToMany(mappedBy = "buyer")
    private Set<Act> acts = new HashSet<>();

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Act> getActs() {
        return acts;
    }

        public void setActs(Set<Act> acts) {
        this.acts = acts;
    }
}
