package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{customerId}")
    public Customer viewCustomer(@PathVariable int customerId) {
        return customerRepository.findById(customerId);
    }

    @GetMapping("/{customerId}/purchase-history")
    public List<Book> viewPurchaseHistory(@PathVariable int customerId) {
        Customer customer = customerRepository.findById(customerId);
        if (customer != null) {
            return customer.getPurchaseHistory();
        }
        return null;
    }

    @PostMapping("/{customerId}/add-to-purchase-history")
    public void addToPurchaseHistory(@PathVariable int customerId, @RequestBody Book book) {
        Customer customer = customerRepository.findById(customerId);
        if (customer != null) {
            customer.addToPurchaseHistory(book);
            customerRepository.save(customer);
        }
    }

    @DeleteMapping("/{customerId}/remove-from-purchase-history")
    public void removeFromPurchaseHistory(@PathVariable int customerId, @RequestBody Book book) {
        Customer customer = customerRepository.findById(customerId);
        if (customer != null) {
            customer.getPurchaseHistory().remove(book);
            customerRepository.save(customer);
        }
    }

    @GetMapping("/{customerId}/view-cart")
    public Cart viewCart(@PathVariable int customerId) {
        Customer customer = customerRepository.findById(customerId);
        if (customer != null) {
            return customer.getCart();
        }
        return null;
    }
}
