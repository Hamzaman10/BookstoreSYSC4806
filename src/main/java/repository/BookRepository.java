package repository;

import java.util.List;

import entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book, Integer>{

    List<Book> findByBookName(String bookName);

    Book findById(int id);
}
