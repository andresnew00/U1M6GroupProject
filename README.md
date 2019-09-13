#  REST Web Service Group Project 
 REST Web Service Group Project - This project involves creating a simple database-backed REST web service for a rental store using Agile development techniques in a team setting. You are responsible for designing and documenting the REST API and implementing the controller, service layer, DAO, Java data objects, and unit tests for the application based on an existing database structure.

### Prerequisites

MySQl, Java, Spring boot + dependencies: 
 + Spring Web
 + JDBC API
 + MySQL Driver

## Running the tests

Set up a database according to the properties given, then set up your application.properties file to match your database
and password, then using this route U1M6GroupProject/U1M6Summative/src/test/java/com/company/U1M6Summative/ , run the U1M6SummativeApplicationTests.java to run tests against the code provided.

### Break down into end to end tests

These tests are created to demonstrate that this application is able to handle CRUD, utilizing TDD and Red Green Refactor

```
    @Before
    public void setUp() throws Exception {
        itemDao.findAll().forEach(item -> {
            itemDao.deleteItem(item.getItemId());
        });

        item = new Item();
        item.setDailyRate(new BigDecimal("10.99"));
        item.setDescription("Test");
        item.setName("Saw");

        item2 = new Item();
        item2.setDailyRate(new BigDecimal("10.99"));
        item2.setDescription("Test Test");
        item2.setName("Drill");

    }

    @Test
    public void saveItem() {
        item = itemDao.saveItem(item);
        assertEquals(1, itemDao.findAll().size());
    }

    @Test
    public void findOne() {
        item = itemDao.saveItem(item);
        Item testCase = itemDao.findOne(item.getItemId());
        assertEquals(testCase, item);

    }
        @Test
    public void updateItem() {
        item = itemDao.saveItem(item);
        item.setDescription("Value Changed");
        itemDao.updateItem(item);
        Item testCase = itemDao.findOne(item.getItemId());
    }

    @Test
    public void deleteItem() {
        item = itemDao.saveItem(item);
        itemDao.deleteItem(item.getItemId());
        Item testCase = itemDao.findOne(item.getItemId());
        assertNull(testCase);
    }

    @Test
    public void findAll() {

        itemDao.saveItem(item);
        itemDao.saveItem(item2);

        List<Item> items = itemDao.findAll();

        assertEquals(2, items.size());

    }

    @Test
    public void findAllByInvoiceItem() {

        itemDao.saveItem(item);
        itemDao.saveItem(item2);

        List<Item> items = itemDao.findAll();

        assertEquals(2, items.size());
    }
```

## Built With

* [Spring](https://spring.io/docs) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://dev.mysql.com/doc/) - Used to generate Database Tables

## Authors

* **Gilbert Medina** - *Customer* - [JayMedina0](https://github.com/JayMedina0)
* **Patrick Hussey** - *Item* - [Husspm](https://github.com/Husspm)
* **Andres Inciarte** - *Invoice* - [andresnew00](https://github.com/andresnew00)
* **Delcie Dion** - *Invoice Item* - [dcdi22](https://github.com/dcdi22)
* **Ahmed Kaahin** - *Testing* - [akahin](https://github.com/akahin)

## Acknowledgments

* DEEP
* Manik
* Keshwan
