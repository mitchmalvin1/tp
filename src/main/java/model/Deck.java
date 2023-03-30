package model;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
    private String deckName;
    private DeckUUID deckUUID;

    private ArrayList<CardUUID> cards = new ArrayList<>();
    private ArrayList<TagUUID> tags = new ArrayList<>();

    public Deck(String deckName) {
        this.deckName = deckName;
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }

    public Deck(String deckName, CardUUID cardUUID) {
        this.deckName = deckName;
        this.cards.add(cardUUID);
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }

    public Deck(String deckName, TagUUID tagUUID) {
        this.deckName = deckName;
        this.tags.add(tagUUID);
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }
    public Deck(String deckName, TagUUID tagUUID, CardUUID cardUUID) {
        this.deckName = deckName;
        this.tags.add(tagUUID);
        this.cards.add(cardUUID);
        this.deckUUID = new DeckUUID(UUID.randomUUID());
    }

    public String getDeckName() {
        return deckName;
    }

    public DeckUUID getDeckUUID() {
        return deckUUID;
    }

    public ArrayList<CardUUID> getCardsUUID() {
        return cards;
    }

    public ArrayList<TagUUID> getTagsUUID() {
        return tags;
    }

    public void editDeckName(String newDeckName) {
        this.deckName = newDeckName;
    }
    public void addCard(CardUUID cardUUID) {
        cards.add(cardUUID);
    }
    public void addTag(TagUUID tagUUID) {
        this.tags.add(tagUUID);
    }

    @Override
    public String toString() {
        return "Deck name : " + deckName + ", deck uuid : " + deckUUID;
    }
}