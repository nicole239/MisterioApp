package com.nicole.misterioapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Notebook implements Parcelable {
    // Contains three dictionaries (one for each CardType)
    //Each dictionary has key: cardID, value: Discovery
    private Map<CardType,Map<Integer, Discovery>> discoveries;

    public Notebook(Deck fullDeck, Deck playerDeck) {
        discoveries = new LinkedHashMap<>();
        for (CardType cardType : CardType.values()){
            discoveries.put(cardType, mapCardTypeDiscoveries(cardType, fullDeck, playerDeck));
        }
    }

    private Map<Integer,Discovery> mapCardTypeDiscoveries(CardType type, Deck fullDeck, Deck playerDeck){
        Map<Integer, Discovery> map = new LinkedHashMap<>();
        for(AbstractCard card : fullDeck.getDeck(type)){
            map.put(card.id, (playerDeck.FindCards(card) == -1 ? Discovery.UNKNOWN : Discovery.INNOCENT));
        }
        return map;
    }

    public void Annotate(CardType cardType, int cardId, Discovery discovery){
        discoveries.get(cardType).put(cardId, discovery);
    }

    public Map<Integer, Discovery> getNotebookPageDiscoveries(CardType notebookPage){
        return discoveries.get(notebookPage);
    }

    //Parcelable interface required functions
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.discoveries.size());
        for (Map.Entry<CardType, Map<Integer, Discovery>> entry : this.discoveries.entrySet()) {
            dest.writeInt( entry.getKey().ordinal());

            dest.writeInt(entry.getValue().size());
            for(Map.Entry<Integer, Discovery> internalEntry : entry.getValue().entrySet()){
                dest.writeInt(internalEntry.getKey());
                dest.writeInt(internalEntry.getValue().ordinal());
            }
        }
    }

    public void readFromParcel(Parcel source) {
        int discoveriesSize = source.readInt();
        this.discoveries = new LinkedHashMap<>(discoveriesSize);
        for (int i = 0; i < discoveriesSize; i++) {
            int tmpKey = source.readInt();
            CardType key = CardType.values()[tmpKey];
            int cardTypeDiscoveriesSize = source.readInt();
            Map<Integer, Discovery> map = new LinkedHashMap<>();
            for(int j = 0; j < cardTypeDiscoveriesSize; j++){
                int cardId = source.readInt();
                Discovery discovery = Discovery.values()[source.readInt()];
                map.put(cardId, discovery);
            }
            this.discoveries.put(key, map);
        }
    }

    protected Notebook(Parcel in) {
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Notebook> CREATOR = new Parcelable.Creator<Notebook>() {
        @Override
        public Notebook createFromParcel(Parcel source) {
            return new Notebook(source);
        }

        @Override
        public Notebook[] newArray(int size) {
            return new Notebook[size];
        }
    };
}
