package com.nicole.misterioapp.model;

public abstract class AbstractCard {
    public int id;
    public String name;
    public CardType type;

    public AbstractCard(int id, String name, CardType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCard card = (AbstractCard) o;
        return this.id == card.id && this.type == card.type;
    }
}
