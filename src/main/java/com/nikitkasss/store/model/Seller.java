package com.nikitkasss.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="abstract_user_id")
@Table(name="Seller")
public class Seller extends AbstractUser{

    @ManyToOne
    @JoinColumn(name="position_id")
    private Position position;

    @OneToMany(mappedBy = "seller")
    private Set<Act> acts = new HashSet<>();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<Act> getActs() {
        return acts;
    }

    public void setActs(Set<Act> acts) {
        this.acts = acts;
    }
}
