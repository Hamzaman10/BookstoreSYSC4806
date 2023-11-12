package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Owner extends User implements Serializable {

    @OneToOne
    @JoinColumn(name = "owners_store_id", referencedColumnName = "id")
    private BookStoreManagement ownersStore;

    @Id
    @GeneratedValue
    private long id;

    public Owner(){};
    public Owner(String email, String phoneNumber, String username, String password, String name, String address) {
        super(email, phoneNumber, username, password, name, address);
        this.setOwnersStore(new BookStoreManagement());
    }

    public void addBookToStore(Book book){
        ownersStore.addBook(book);
    }

    public void removeBookFromStore(long id){
        ownersStore.removeBook(id);
    }

    public void updateStoreQuantity(long id, int amount){
        ownersStore.updateQuantity(id, amount);
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

    public void setId(Integer id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}
