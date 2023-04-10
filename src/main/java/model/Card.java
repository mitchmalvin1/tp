package model;

import java.util.ArrayList;
import java.util.UUID;
import utils.exceptions.InvalidUUIDException;

//to be made into an abstract class containing a few types of Cards later, for now just a single Card will do
public class Card {
    private CardUUID uuid;
    private String question;
    private String answer;
    private ArrayList<TagUUID> tags = new ArrayList<>();

    private ArrayList<DeckUUID> decks = new ArrayList<>();

    // Temporary constructor for Card, to be revised later to also consider uuid and tag etc
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.uuid = new CardUUID(UUID.randomUUID());
    }

    /**
     * Constructor function where user specifies the UUID himself This is private and is only called by the static
     * factory method createCardWithUUID()
     *
     * @param question The question in the card.
     * @param answer   The answer in the card.
     * @param uuidStr  The custom UUID String that the user specifies for the card.
     * @throws InvalidUUIDException when the UUID specified is not in 03658854-e5d4-468f-8c41-74917e5d4515 format
     */
    private Card(String question, String answer, String uuidStr) throws InvalidUUIDException {
        this.question = question;
        this.answer = answer;
        try {
            this.uuid = new CardUUID(UUID.fromString(uuidStr));
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDException();
        }
    }

    /**
     * A method that calls a private constructor function where user can specify the UUID himself. This method is mainly
     * used for unit-testing purpose
     *
     * @param question The question in the card
     * @param answer   The answer in the card
     * @param uuidStr  The custom UUID String that the user wants for the card
     * @return
     */
    public static Card createCardWithUUID(String question, String answer, String uuidStr) throws InvalidUUIDException {
        return new Card(question, answer, uuidStr);
    }

    public CardUUID getUuid() {
        return this.uuid;
    }

    public ArrayList<TagUUID> getTagsUUID() {
        return this.tags;
    }

    public ArrayList<DeckUUID> getDecksUUID() {
        return this.decks;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void addTag(TagUUID tagUUID) {
        tags.add(tagUUID);
    }

    public void addDeck(DeckUUID deckUUID) {
        decks.add(deckUUID);
    }

    public void removeTag(TagUUID tagUUID) {
        tags.remove(tagUUID);
    }

    public void removeDecks(DeckUUID deckUUID) {
        decks.remove(deckUUID);
    }

    @Override
    public String toString() {
        return "\t[" + this.uuid + "]\n" +
                "\tQn:\t" + this.question +
                "\n\tAns:\t" + this.answer + "\n";
    }

    public String toTruncatedString() {
        boolean isQnTooLong = this.question.length() > 50;
        boolean isAnsTooLong = this.answer.length() > 50;

        String uuidStr = "\t[" + this.uuid + "]";
        String warningStr =
                (isQnTooLong || isAnsTooLong) ? "\n\tNote:\tActual question or answer is too long, string truncated"
                        : "";

        String questionStr = isQnTooLong ? this.question.substring(0, 50) : this.question;
        String answerStr = isAnsTooLong ? this.answer.substring(0, 50) : this.answer;

        return uuidStr + "\n\tQn:\t" + questionStr + "\n\tAns:\t" + answerStr + warningStr + "\n";
    }
}
