package controller;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/view/{cartId}")
    public Cart viewCart(@PathVariable Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @PostMapping("/add-book")
    public void addBookToCart(@RequestBody Book book, @RequestParam Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.addBook(book);
            cartRepository.save(cart);
        }
    }

    @DeleteMapping("/remove-book")
    public void removeBookFromCart(@RequestBody Book book, @RequestParam Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.removeBook(book);
            cartRepository.save(cart);
        }
    }
    @PostMapping("/checkout/{cartId}")
    public void checkoutCart(@PathVariable Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.checkout();
            cartRepository.save(cart);
        }
    }
}