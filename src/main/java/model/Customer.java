package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User implements Serializable {

    @OneToMany
    List<Book> purchaseHistory;

    @OneToOne
    private Cart cart;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public Customer(){
        super();
    };

    public Customer(String email, String phoneNumber, String username, String password, String name, String address) {
        super(email, phoneNumber, username, password, name, address);
        //this.cart = new Cart(this);
        this.purchaseHistory = null;
    }

    public void addToPurchaseHistory(Book book){
        purchaseHistory.add(book);
    }

    public void printOutPurchaseHistory(){
        int val = 0;
        if(purchaseHistory.isEmpty()){
            System.out.println("The purchase history is empty!");
            return;
        }
        while(purchaseHistory.size() > val){
            System.out.println(purchaseHistory.get(val).getBookName());
            val++;
        }
    }

    public void checkout(){
        PaymentProcessor.processPayment(this,cart);
        cart.clearItems();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart){
        this.cart = cart;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPurchaseHistory(List history){
        this.purchaseHistory = history;
    }

    public List <Book> getPurchaseHistory(){
        return purchaseHistory;
    }
}

