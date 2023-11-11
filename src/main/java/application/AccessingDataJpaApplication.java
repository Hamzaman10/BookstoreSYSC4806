package application;

import entity.Book;
import entity.BookStoreManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.BookStoreManagementRepository;

@SpringBootApplication
@EntityScan("entity")
@EnableJpaRepositories("repository")
public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner group22Demo(BookRepository bookRepository, BookStoreManagementRepository bookStoreRepository) {
        return (args) -> {
            // save a few Books
            bookRepository.save(new Book(123,"testbook1","kyler","group22",3,30.00));
            bookRepository.save(new Book(1234,"testbook2","kyler","group22",3,30.00));
            bookRepository.save(new Book(1235,"testbook3","kyler","group22",3,30.00));

            // fetch all Books
            log.info("Books found with findAll():");
            log.info("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }
            log.info("");

            // fetch an individual Book by ID
            Book book1 = bookRepository.findById(1);
            log.info("Book found with findById(1):");
            log.info("--------------------------------");
            log.info(book1.toString());
            log.info("");

            // fetch Books by bookName
            log.info("Books found with findByBookName('testbook1'):");
            log.info("--------------------------------------------");
            bookRepository.findByBookName("testbook1").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");

            //Save a BookStoreManagement that contains a Book
            BookStoreManagement bookStoreTest = new BookStoreManagement();
            bookStoreTest.addBook(bookRepository.findById(1));
            log.info(bookStoreTest.toString());
            bookStoreRepository.save(bookStoreTest);

            // fetch an individual BookStore by ID
            BookStoreManagement bookStore2 = bookStoreRepository.findById(1);
            log.info("BookStoreManagement found with findById(1):");
            log.info("--------------------------------");
            log.info(bookStore2.getBookList().toString());
            log.info("");
        };
    }

}
