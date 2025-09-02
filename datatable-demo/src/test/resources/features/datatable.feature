Feature: DataTable demonstration

  Scenario: List of usernames (DataTable.asList())
    # This shows the usage of dataTable.asList(String.class)
    Given the following usernames
      | alice |
      | bob   |
      | carol |
    Then I should have 3 usernames in the list

  Scenario: List of usernames (DataTable.asMap())
    # This shows the usage of dataTable.asMap(String.class, String.class)
    Given the following student and teacher usernames
      | alice | mrsmith |
      | bob   | msjones |
      | carol | mrbrown |
    Then I should have 3 student teacher groups

  Scenario: Demonstrating DataTable.asMaps()
    # This shows the usage of dataTable.asMaps(String.class, String.class)
    # Each row is converted to a Map<String, String> where the key is the column header
    # and the value is the cell value for that row.
    # Using `asMaps()` on a `DataTable` means each `Map<String, String>` in the resulting `List` represents one row from your scenario, where the keys are the column headers and the values are the corresponding cell values for that row.
    #
    # For your example in `datatable.feature`:
    #
    #```gherkin
    #| username | password | active |
    #| user1    | pass1    | true   |
    #| user2    | pass2    | false  |
    #```
    #
    #`asMaps()` will produce a `List` with two maps:
    #
    #- The first map: `{username=user1, password=pass1, active=true}`
    #- The second map: `{username=user2, password=pass2, active=false}`
    #
    #So, each map contains the details of one user.
    Given the following users with details:
      | username | password | active |
      | user1    | pass1    | true   |
      | user2    | pass2    | false  |
    Then I should see 1 user active

  Scenario: Demonstrating DataTable for Pojo class
    # This shows the usage of dataTable.asList(PojoClass.class)
    Given the library is setup with the following book details:
      | title                 | author              | year | genre     |
      | The Great Gatsby      | F. Scott Fitzgerald | 1925 | Fiction   |
      | To Kill a Mockingbird | Harper Lee          | 1960 | Fiction   |
      | 1984                  | George Orwell       | 1949 | Dystopian |
    When I process the book details
    Then I should see the the count of books published before 1950 as 2

  Scenario Outline: Borrow books for user
    Given the user "<user>" borrows the following books:
      | title    | days |
      | <title1> | <d1> |
      | <title2> | <d2> |
    Then the user's borrowed book count should be <count>
    Examples:
      | user  | title1                | d1 | title2           | d2 | count |
      | alice | The Great Gatsby      | 7  | 1984             | 14 | 2     |
      | bob   | To Kill a Mockingbird | 10 |                  |    | 1     |
      | carol | 1984                  | 5  | The Great Gatsby | 5  | 2     |

#  Scenario: Using DataTable to test multiple login attempts
#    When I try to login with the following credentials:
#      | username | password    | expectedError         |
#      | alice    | wrongpass   | Invalid credentials   |
#      | bob      |             | Password required     |
#      |          | secret      | Username required     |
#      | root     | foo         | Account locked        |
#    Then I should see error messages for all

  # This scenario demonstrates how to use DataTable to represent
  #   a matrix of data as nested lists. Each row in the DataTable corresponds
  #   to a list, and the entire table is a list of these lists.
  # Lists of Lists (asLists)
  #  When to use:
  #  When you have a table of data where row and column positions matter, but you don’t care about column headers, or want raw access.
  # Each row is a list of strings, so you get List<List<String>>.
  # Use cases:
  # Matrices, board games, or generic CSV-like data.
  Scenario: Raw matrix data as nested lists
    Given the following matrix
      | 1 | 2 | 3 |
      | 4 | 5 | 6 |
      | 7 | 8 | 9 |

  # This scenario demonstrates how to use DataTable to represent
  #   a table of data as a map of maps. Each row in the DataTable corresponds
  #   to a map where the key is the column header and the value is the cell value.
  #   The entire table is represented as a map where the key is the value from the first column,
  #   and the value is another map representing the rest of the row.

  # Maps of Maps (asMaps)
  #  When to use:
  #  When you have a table of data where you want to access rows by a specific key (like an ID or name) and also want to access columns by their headers.
  # Each row is a map of column header to cell value, and the entire table is a map of the first column to these row maps, so you get Map<String, Map<String, String>>.
  #  Map of Maps (asMaps with custom key or via transformation)
  #    When to use:
  #    When you have a table where one particular column can act as a row key, or you want to lookup a full row by a unique field.
  #
  #  Note: Cucumber’s built-in API doesn’t directly provide a one-call .asMapOfMaps() but you can create one easily by processing a list of maps.
  #
  #  Scenario Example
  #
  #  Let's imagine we have a table of users, and "username" is unique and should be the main map key:
  # Use cases:
  #
  #Test lookup tables, permission matrices, configuration where a "row key" is important for access.
  Scenario: User directory lookup
    Given the following users table
      | username | fullname | email         |
      | alice    | Alice S. | alice@xyz.com |
      | bob      | Bob Q.   | bob@xyz.com   |
