package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class Owner extends User implements Serializable {

    @OneToOne
    BookStoreManagement ownersStore = new BookStoreManagement();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Owner(){};
    public Owner(String email, String phoneNumber, String username, String password, String name, String address) {
        super(email, phoneNumber, username, password, name, address);
    }

    public void addBookToStore(Book book){
        ownersStore.addBook(book);
    }

    public void removeBookFromStore(Book book){
        ownersStore.removeBook(book.getIsbn());
    }

    public void updateStoreQuantity(int isbn, int amount){
        ownersStore.updateQuantity(isbn, amount);
    }

    public Collection<Book> getBookStore(){
        return ownersStore.getBookList();
    }

    public BookStoreManagement getOwnersStore() {
        return ownersStore;
    }
    public void setOwnersStore(BookStoreManagement store){
        this.ownersStore = store;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
