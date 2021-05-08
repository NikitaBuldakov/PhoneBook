# PhoneBook
The project maintains a small server application that stores information about users and their phone book's entries. Each user has their entry/entries stored in phone book. 

To run this app you will need:
- TomCat (version 9+);
- PostgreSQL (version 12+);
- JDK (version 14+).

TO RUN THE APP:
- Run the server after configuration process. 
- Configuration process can be found in the attachments to the project;



RELEVANT PATHS:
- Path to the actual code: PhoneBook/phone_book/src/main/java/org/buldakov/PhoneBook/
- Path to JUnit tests: PhoneBook/phone_book/src/test/java/org/buldakov/PhoneBook/


PROJECT STRUCTURE:
DB consists of 2 tables:
- Users
- PhoneBook
Users table stores information about their name and phone number. 
PhoneBook table stores information about all users phone records.
User has no foreign keys in any way.
PhoneBook has a foreign key to the User table and has a 1: n relationship, and when an entry in User table is deleted, the dependent entries in the PhoneBook table are cascaded.


TECHNOLOGIES USED IN THE DEVELOPMENT: 
- Backend: 
  - Java (version 8.281);
  - Spring Modules:
    - Core;
    - MVC;
    - Boot;
  - TomCat (version 9.0.43) for server deployment;
  - PostgreSQL (version 12) for database;
  - JUnit (version 4) for tests;
