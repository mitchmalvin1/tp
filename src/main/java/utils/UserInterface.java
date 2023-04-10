package utils;

import java.util.Scanner;

import model.Card;
import model.CardList;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.Tag;
import model.TagList;
import utils.enums.StringArt;
import utils.exceptions.InkaException;

public class UserInterface {
    private static final int LINE_LENGTH = 100;
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void printUserPrompt() {
        System.out.print("> ");
    }

    public String getUserInput() {
        printUserPrompt();
        return scanner.nextLine().trim();
    }

    public void printDivider() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    public void printGreeting() {
        printDivider();
        System.out.println(StringArt.INKA.art);
        System.out.println("Welcome to Inka ! Say no more to failing exams as Inka will be your lord and saviour!");
        printDivider();
    }

    public void printBye() {
        printDivider();
        System.out.println(StringArt.BYE.art);
        System.out.println("\n Bye! All the best for your exams man!!!");
        printDivider();
    }

    public void printHelp(String helpMessage) {
        printDivider();
        System.out.print(helpMessage);
        printDivider();
    }

    public void printNumOfQuestions(CardList cardList) {
        System.out.println("You now have " + cardList.size() + " questions in the bank.");
    }

    public void printAddQuestionSuccess() {
        System.out.println("That's a good question for revision later!");
    }

    public void printDeleteSuccess() {
        System.out.println("Too easy ha? You won't see that question again!");
    }

    public void printWrongCommand() {
        System.out.println("Please re-enter a valid command!");
    }

    public void printNoSaveFile() {
        System.out.println("No savedata detected! make new one for uuuuuuuuuuuuuuu");
    }

    public void printSaveSuccess() {
        System.out.println("Deck exported liao");
    }

    public void printLoadSuccess() {
        System.out.println("Deck loaded!");
    }

    public void printCard(CardList cardlist, int id) {
        System.out.println(cardlist.get(id));
    }

    public void printCard(Card card) {
        System.out.println(card);
    }

    public void printException(InkaException e) {
        System.out.println(e.getUiMessage());
    }

    public void printCardList(CardList cardList) {
        if (cardList.isEmpty()) {
            System.out.println("Your current list of cards is empty.");
        } else {
            System.out.println("Here is a list of your cards :\n");
            for (int i = 0; i < cardList.size(); ++i) {
                System.out.println(
                        "\t" + (i + 1) + ".\t" + cardList.get(i).toTruncatedString()); // 1. question  answer
            }
        }
    }

    public void printTagList(TagList tagList) {
        if (tagList.isEmpty()) {
            System.out.println("Your current list of tags is empty.");
        } else {
            System.out.println("Here is your current list of tags:");
            for (int i = 0; i < tagList.size(); ++i) {
                System.out.println((i + 1) + ".\t" + tagList.get(i));
            }
        }
    }

    public void printTags(TagList tagList) {
        if (tagList.isEmpty()) {
            System.out.println("\tThere is no tag.");
        } else {
            System.out.println("\tHere are your tags:");
            for (int i = 0; i < tagList.size(); ++i) {
                System.out.println("\t" + (i + 1) + ".\t" + tagList.get(i));
            }
        }
        System.out.println();
    }

    public void printDeckList(DeckList deckList) {
        if (deckList.isEmpty()) {
            System.out.println("Your current list of decks is empty.");
        } else {
            System.out.println("Here is your current list of decks:");
            for (int i = 0; i < deckList.size(); ++i) {
                System.out.println((i + 1) + ".\t" + deckList.get(i));
            }
        }
    }

    public void printDecks(DeckList deckList) {
        if (deckList.isEmpty()) {
            System.out.println("\tThere is no deck.");
        } else {
            System.out.println("\tHere are your decks:");
            for (int i = 0; i < deckList.size(); ++i) {
                System.out.println("\t" + (i + 1) + ".\t" + deckList.get(i));
            }
        }
    }

    public void printAddTagToCardSuccess(CardUUID cardUUID, String tagName) {
        System.out.println("Successfully added tag " + tagName + " to card " + cardUUID);
    }

    public void printAddCardToDeckSuccess(CardUUID cardUUID, String deckName) {
        System.out.println("Successfully added card " + cardUUID + " to deck " + deckName);
    }

    public void printRemoveCardFromDeckSuccess(CardUUID cardUUID, String deckName) {
        System.out.println("Successfully removed card " + cardUUID + " from deck " + deckName);
    }

    public void printRemoveTagFromDeckSuccess(String tagName, String deckName) {
        System.out.println("Successfully removed tag " + tagName + " from deck " + deckName);
    }

    public void printAddTagToDeckSuccess(String tagName, String deckName) {
        System.out.println("Successfully added tag " + tagName + " to deck " + deckName);
    }

    public void printTagCreationSuccess(String tagName) {
        System.out.println("Tag does not exist.. creating a new tag: " + tagName);
    }

    public void printDeckCreationSuccess() {
        System.out.println("Deck does not exist.. creating a new one");
    }

    public void printRemoveTagFromCard(CardUUID cardUUID, String tagName) {
        System.out.println("Successfully removed tag " + tagName + " from card " + cardUUID);
    }

    public void printRemoveDeckFromCard(CardUUID cardUUID, String deckName) {
        System.out.println("Successfully removed deck " + deckName + " from card " + cardUUID);
    }

    public void printRemoveDeckFromTag(String tagName, String deckName) {
        System.out.println("Successfully removed deck " + deckName + " from tag " + tagName);
    }

    public void printRemoveTagFromTagList(String tagName) {
        System.out.println("Successfully removed tag " + tagName + " from the tag list.");
    }

    public void printRemoveDeckFromDeckList(String deckName) {
        System.out.println("Successfully removed deck " + deckName + " from the deck list.");
    }

    public void printEditTagName(String oldTagName, Tag tag) {
        System.out.println(
                "Tag name has been changed from " + oldTagName + " to " + tag.getTagName());
    }

    public void printEditDeckNameSuccess(String oldDeckName, Deck deck) {
        System.out.println(
                "Deck name has been changed from " + oldDeckName
                        + " to " + deck.getDeckName());
    }

    public void printQuestion(String question) {
        System.out.println("\tQ: " + question);
    }

    public void printAnswer(String answer) {
        System.out.println("\tA: " + answer + "\n");
    }

    public void printExitingRunMode() {
        System.out.println("Exiting run mode!\n");
    }

    public void printWarning() {
        System.out.println("There is no need to input any characters!\n"
                + "Just hitting enter is sufficient to show the answer! Anyway, here is the answer!\n");
    }

    public void printLoadBackup() {
        System.out.println("The original file seems to be corrupted... loading the backup file!");
    }

    public void printLoadBackupSuccess() {
        System.out.println("Backup Loaded!");
    }

    public void corruptedBackup() {
        System.out.println("Corrupted backup file! making a new file");
    }

    public void failedSave(String path) {
        System.out.println("Failed to save at path: " + path);
    }

    public void printPermFail() {
        System.out.println("Failed to set file permissions");
    }
}
