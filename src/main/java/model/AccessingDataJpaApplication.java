package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner group22Demo(BookRepository bookRepository,
                                         BookStoreManagementRepository bookStoreRepository,
                                         CartRepository cartRepository,
                                         OwnerRepository ownerRepository,
                                         CustomerRepository customerRepository) {
        return (args) -> {
            Owner owner1 = new Owner("owneremail", "12345", "Owner", "ImTheBoss","Boss", "bossstreet");
            System.out.println("Owner made");
            BookStoreManagement owner1BookStore = owner1.getOwnersStore();
            bookStoreRepository.save(owner1BookStore);
            System.out.println("bookstore saved");
            ownerRepository.save(owner1);
            System.out.println("owner saved");

            Book book1 = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
            bookRepository.save(book1);
            Book book2 = new Book(128, ":D", "Hamza Zafar", "Carleton", 10,1.99);
            bookRepository.save(book1);

            owner1.getOwnersStore().addBook(book1);
            bookStoreRepository.save(owner1BookStore);
            ownerRepository.save(owner1);
            owner1.getOwnersStore().addBook(book2);
            System.out.println(owner1.getOwnersStore().getBookList());



        };
    }

}
