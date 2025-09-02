package com.example.stepdefs;

import com.example.model.Book;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTableStepDefs {

    private List<String> usernames;
    private Map<String, String> studentToTeacher;
    private List<Map<String, String>> usersList;
    private List<Book> books;

    private final Map<String, List<Map<String, String>>> borrowedBooksByUser = new HashMap<>();
    private String currentUser;

    @DataTableType
    public Book bookEntry(Map<String, String> row) {
        Book book = new Book();
        book.title = row.get("title");
        book.author = row.get("author");
        book.year = Integer.parseInt(row.get("year"));
        book.genre = row.get("genre");

        System.out.println("[LOG] Created Book from DataTable row");
        //System.out.format("[LOG] Book Details - Title: %s, Author: %s, Year: %d, Genre: %s%n",
        //        book.title, book.author, book.year, book.genre);
        //System.out.printf("[LOG] Created Book from DataTable row with title %s\n", book.title);
        return book;
    }

    @Given("the following usernames")
    public void theFollowingUsernames(DataTable dataTable) {
        System.out.println("[LOG] Received the following usernames:");
        usernames = dataTable.asList(String.class);
    }

    @Then("I should have {int} usernames in the list")
    public void iShouldHaveUsernamesInTheList(int arg0) {
        if (usernames == null) {
            throw new PendingException("Usernames list is not initialized");
        }
        if (usernames.size() != arg0) {
            throw new PendingException("Expected " + arg0 + " usernames but got " + usernames.size());
        }
        System.out.println("[LOG] Usernames list contains the expected number of entries: " + arg0);
    }

    @Given("the following student and teacher usernames")
    public void theFollowingStudentAndTeacherUsernames(DataTable dataTable) {
        System.out.println("[LOG] Received the following student and teacher usernames:");
        studentToTeacher = dataTable.asMap(String.class, String.class);

        /*System.out.println("Received the following student and teacher usernames:");
        List<List<String>> rows = dataTable.asLists();
        for (List<String> row : rows) {
            System.out.println("Student: " + row.get(0) + ", Teacher: " + row.get(1));
        }*/
    }

    @Then("I should have {int} student teacher groups")
    public void iShouldHaveStudentTeacherGroups(int arg0) {
        if (studentToTeacher == null) {
            throw new PendingException("Student to teacher map is not initialized");
        }
        if (studentToTeacher.size() != arg0) {
            throw new PendingException("Expected " + arg0 + " student-teacher groups but got " + studentToTeacher.size());
        }
        System.out.println("[LOG] Student to teacher map contains the expected number of entries: " + arg0);
    }

    // Demonstrating DataTable.asMaps()
    @Given("the following users with details:")
    public void theFollowingUsersWithDetails(DataTable dataTable) {
        usersList = dataTable.asMaps();
        //List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
    }

    @Then("I should see {int} user active")
    public void iShouldSeeUserActive(int arg0) {
        if (usersList == null) {
            throw new PendingException("Users list is not initialized");
        }
        long activeCount = usersList.stream()
                .filter(user -> "true".equalsIgnoreCase(user.get("active")))
                .count();
        if (activeCount != arg0) {
            throw new PendingException("Expected " + arg0 + " active users but got " + activeCount);
        }
        System.out.println("[LOG] Active users count matches expected: " + arg0);
    }

    @Given("the library is setup with the following book details:")
    public void the_library_is_setup_with_the_following_book_details(DataTable dataTable) {
        System.out.println("[LOG] Received the following book details:");
        //books.forEach(book -> System.out.println("Title: " + book.title + ", Author: " + book.author + ", Year: " + book.year + ", Genre: " + book.genre));
        books = dataTable.asList(Book.class);
        System.out.println("[LOG] Books created from DataTable/POJO:");
        for (Book b : books) {
            System.out.printf("[LOG] Book: %s by %s (%d) and genre %s%n", b.title, b.author, b.year, b.genre);
        }
        // You can now work with strongly-typed objects!
    }

    @When("I process the book details")
    public void i_process_the_book_details() {
        System.out.println("[LOG] Processing the book details");
    }

    @Then("I should see the the count of books published before {int} as {int}")
    public void i_should_see_the_the_count_of_books_published_before_as(Integer year, Integer expectedCount) {
        System.out.println("[LOG] The count of books published before " + year + " as " + expectedCount);
        // You can add assertions or further processing here as needed
        long actualCount = books.stream()
                .filter(book -> book.year < year)
                .count();
        //assertTrue(actualCount == expectedCount, "Expected " + expectedCount + " books but found " + actualCount);
        assertEquals(actualCount, (int) expectedCount, "Expected " + expectedCount + " books but found " + actualCount);
    }

    @Given("the user {string} borrows the following books:")
    public void theUserBorrowsTheFollowingBooks(String user, DataTable dataTable) {
        //        dataTable.asMaps(String.class, String.class).forEach(row -> {
//            String title = row.get("title");
//            String author = row.get("author");
//            int year = Integer.parseInt(row.get("year"));
//            String genre = row.get("genre");
//            System.out.printf("[LOG] User %s borrows book: %s by %s (%d) - Genre: %s%n", user, title, author, year, genre);
//        });
        // Using asMaps to get list of maps. Each map represents a row with column names as keys.
        //List<Map<String, String>> books = dataTable.asMaps(String.class, String.class);
        // Remove the map with null keys if present
        //books.removeIf(map -> map.containsKey(null));
        List<Map<String, String>> books = dataTable.asMaps(String.class, String.class)
                .stream()
                .filter(map -> map.values().stream().anyMatch(val -> val != null && !val.isBlank()))
                .toList();
        borrowedBooksByUser.put(user, books);
        currentUser = user;
        books.forEach(row -> {
            String title = row.get("title");
            String days = row.get("days");
            System.out.printf("[LOG] User %s borrows book: %s for %s days%n", user, title, days);
        });
        //System.out.println("[LOG] Count of books borrowed by user " + user + ": " + books.size());
        System.out.printf("[LOG] User %s borrowed %d books%n", user, books.size());
    }

    @Then("the user's borrowed book count should be {int}")
    public void theUsersBorrowedBookCountShouldBeCount(int count) {
            List<Map<String, String>> books = borrowedBooksByUser.get(currentUser);
            int actualCount = (books == null) ? 0 : books.size();
            System.out.printf("[LOG] User %s borrowed %d books as expected%n", currentUser, count);
            assertEquals(count, actualCount, "Expected " + count + " borrowed books but found " + actualCount);
    }

    @Given("the following matrix")
    public void the_following_matrix(DataTable table) {
        List<List<String>> matrix = table.asLists();
        System.out.println("[LOG] Matrix (List of Lists):");
        for (int row = 0; row < matrix.size(); row++) {
            System.out.printf("[LOG] Row %d: %s%n", row, matrix.get(row));
        }
        // Access: matrix.get(row).get(col)
    }

    @Given("the following users table")
    public void the_following_users_table(DataTable table) {
        // First: get List<Map<String, String>>
        List<Map<String, String>> usersList = table.asMaps();

        // Next: build Map<username, Map<column, value>>
        Map<String, Map<String, String>> userDirectory = new HashMap<>();
        for (Map<String, String> row : usersList) {
            String username = row.get("username");
            userDirectory.put(username, row);
        }

        // Log
        System.out.println("[LOG] Map of Map (user directory):");
        for (Map.Entry<String, Map<String, String>> entry : userDirectory.entrySet()) {
            System.out.printf("[LOG] Username: %s, Full user info: %s%n", entry.getKey(), entry.getValue());
        }

        // Example access
        Map<String, String> aliceRow = userDirectory.get("alice");
        System.out.printf("[LOG] Alice's email: %s%n", aliceRow.get("email"));
    }
}
