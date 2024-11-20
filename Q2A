// LibraryManagementSystem.java

import java.util.*;

// Custom Exception
class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}

// Abstract class LibraryItem
abstract class LibraryItem {
    protected String title;
    protected String itemID;
    protected boolean isAvailable;

    public LibraryItem(String title, String itemID) {
        this.title = title;
        this.itemID = itemID;
        this.isAvailable = true;
    }

    public abstract void borrow() throws ItemNotAvailableException;
    public abstract void returnItem();

    public String getTitle() {
        return title;
    }

    public String getItemID() {
        return itemID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

// Concrete class Book
class Book extends LibraryItem {
    private String author;
    private String genre;

    public Book(String title, String itemID, String author, String genre) {
        super(title, itemID);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public void borrow() throws ItemNotAvailableException {
        if (!isAvailable) {
            throw new ItemNotAvailableException("The book '" + title + "' is not available.");
        }
        isAvailable = false;
        System.out.println("Borrowed the book: " + title);
    }

    @Override
    public void returnItem() {
        isAvailable = true;
        System.out.println("Returned the book: " + title);
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }
}

// Concrete class DVD
class DVD extends LibraryItem {
    private String director;
    private int duration; // in minutes

    public DVD(String title, String itemID, String director, int duration) {
        super(title, itemID);
        this.director = director;
        this.duration = duration;
    }

    @Override
    public void borrow() throws ItemNotAvailableException {
        if (!isAvailable) {
            throw new ItemNotAvailableException("The DVD '" + title + "' is not available.");
        }
        isAvailable = false;
        System.out.println("Borrowed the DVD: " + title);
    }

    @Override
    public void returnItem() {
        isAvailable = true;
        System.out.println("Returned the DVD: " + title);
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }
}

// Library class to manage library items
class Library {
    private List<LibraryItem> items = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    // Polymorphism in action - Dynamically calling borrow and returnItem
    public void borrowItem(LibraryItem item) {
        try {
            item.borrow();
        } catch (ItemNotAvailableException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Operation on '" + item.getTitle() + "' completed.");
        }
    }

    public void returnItem(LibraryItem item) {
        item.returnItem();
        System.out.println("Operation on '" + item.getTitle() + "' completed.");
    }

    // Method to search by title
    public List<LibraryItem> searchByTitle(String title) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                results.add(item);
            }
        }
        return results;
    }

    // Method to search by itemID
    public LibraryItem searchByID(String itemID) {
        for (LibraryItem item : items) {
            if (item.getItemID().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    // Display items grouped by type
    public void displayItems() {
        System.out.println("Books in the library:");
        for (LibraryItem item : items) {
            if (item instanceof Book) {
                System.out.println(((Book) item).getTitle());
            }
        }
        System.out.println("\nDVDs in the library:");
        for (LibraryItem item : items) {
            if (item instanceof DVD) {
                System.out.println(((DVD) item).getTitle());
            }
        }
    }
}

// Main class
class Q2A {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding items to the library
        Book book1 = new Book("The Great Gatsby", "B1", "F. Scott Fitzgerald", "Fiction");
        DVD dvd1 = new DVD("Inception", "D1", "Christopher Nolan", 148);
        Book book2 = new Book("1984", "B2", "George Orwell", "Dystopian");

        library.addItem(book1);
        library.addItem(dvd1);
        library.addItem(book2);

        // Search by title
        System.out.println("Searching by title '1984':");
        List<LibraryItem> searchResults = library.searchByTitle("1984");
        for (LibraryItem item : searchResults) {
            System.out.println("Found item: " + item.getTitle());
        }

        // Search by ID
        System.out.println("\nSearching by ID 'B1':");
        LibraryItem itemByID = library.searchByID("B1");
        if (itemByID != null) {
            System.out.println("Found item: " + itemByID.getTitle());
        } else {
            System.out.println("Item not found.");
        }

        // Display all items
        System.out.println("\nDisplaying all library items:");
        library.displayItems();

        // Borrow items
        library.borrowItem(book1);  // Borrow the book
        library.borrowItem(dvd1);   // Borrow the DVD
        library.borrowItem(book1);  // Try to borrow again (should throw an exception)

        // Return items
        library.returnItem(book1);
        library.returnItem(dvd1);
    }
}
