package utils.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;
import model.Card;
import model.CardUUID;
import model.Memory;
import model.TagUUID;
import org.junit.jupiter.api.Test;
import utils.exceptions.CardNotFoundException;
import utils.exceptions.InkaException;
import utils.exceptions.StorageCorrupted;
import utils.storage.json.JsonStorage;
import model.CardList;

public class JsonStorageTest {

    private static final Path TEST_DATA_FOLDER = Path.of("src/test/data/storage");

    /* Test cases */
    private static final Path EMPTY_FILE = TEST_DATA_FOLDER.resolve("empty.json");
    private static final Path MALFORMED_FILE = TEST_DATA_FOLDER.resolve("malformed.json");
    private static final Path VALID_FILE = TEST_DATA_FOLDER.resolve("valid.json");

    //valid file properties

    private static int validFileCardListSize = 2;
    private static String card1Uuid = "1ddd9a67-f56c-4914-99c0-2f90c580f0e9";
    private static String card1Q = "fdfds";
    private static String card1A = "ffffffghgg";
    private static String card1DeckUuid = "c83e08ad-e5b7-4812-9dd1-4b44504386ad";

    private static int card1TagSize = 1;
    private static String card1TagUuid = "03658854-e5d4-468f-8c41-74917e5d4515";


    private static String card2Uuid = "619c689d-395a-4bb8-ab00-6ae9972bb929";
    private static String card2Q = "question2";



    @Test
    public void load_emptyFile() {
        Storage storage = new JsonStorage(EMPTY_FILE.toString());
        assertThrows(StorageCorrupted.class, storage::load, "Expected a StorageCorrupted exception");
    }

    @Test
    public void load_malformedFile() {
        Storage storage = new JsonStorage(MALFORMED_FILE.toString());
        assertThrows(StorageCorrupted.class, storage::load, "Expected a StorageCorrupted exception");
    }

    @Test
    public void load_validFile() throws InkaException {
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();


    }



    @Test
    public void load_validFile_cardList() throws InkaException {
        //check if it is loading 2 cards
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();
        int cardListSize = cardList.size();
        int expectedSize = validFileCardListSize;
        assertEquals(cardListSize, expectedSize);

    }

    @Test
    public void load_validFile_cardUuid() throws InkaException {
        //check if it is loading 2 cards
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        String card1UuidStr = card1UuidObj.toString();

        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        CardUUID card1TestUuidobj =  card1.getUuid();
        String card1TestUuidStr = card1TestUuidobj.toString();

        assertEquals(card1UuidStr, card1TestUuidStr);
        //todo need to add case for checking for malformed UUID

    }

    @Test
    public void load_validFile_cardTags() throws InkaException {
        //check if it is loading 2 cards
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        ArrayList<TagUUID> card1Tags =  card1.getTagsUUID();
        int  expectedSize = card1TagSize;


        assertEquals(expectedSize, card1Tags.size());
        //todo need to add case for checking for malformed UUID

    }

//    @Test
//    public void load_validFile() throws InkaException {
//        Storage storage = new JsonStorage(VALID_FILE.toString());
//        Memory memory = storage.load();
//
//        CardList cardList = memory.getCardList();
//        boolean has
//        try {
//            assertEquals(card1, cardList.findCardFromUUID(new CardUUID(UUID.fromString(UUID_1))));
//            assertEquals(card2, cardList.findCardFromUUID(new CardUUID(UUID.fromString(UUID_2))));
//        } catch (CardNotFoundException e) {
//            assertEquals("Card cannot be found", e.getMessage());
//        }
//
//    }
}
