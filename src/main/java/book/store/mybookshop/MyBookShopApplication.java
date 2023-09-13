package book.store.mybookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "book.store.mybookshop")
public class MyBookShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBookShopApplication.class, args);
    }

}
