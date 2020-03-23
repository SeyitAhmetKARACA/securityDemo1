package com.securityexample.demo.initializer;

import com.securityexample.demo.model.Book;
import com.securityexample.demo.model.User;
import com.securityexample.demo.repository.BookRepository;
import com.securityexample.demo.repository.UserRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class demoInitializer implements SmartInitializingSingleton {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public demoInitializer(BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Long id1 = 1L;
        Long id2 = 2L;

        this.bookRepository.save(new Book("sefiller","victor hugo"));
        this.bookRepository.save(new Book("sefiller2","victor hugo2"));
        this.bookRepository.save(new Book("sefiller3","victor hugo3"));

        this.bookRepository.save(new Book("suc ve ceza","fuch ve ceza"));
        this.bookRepository.save(new Book("suc ve ceza2","fuch ve ceza2"));
        this.bookRepository.save(new Book("suc ve ceza3","fuch ve ceza3"));

        User sak = new User("sak",this.passwordEncoder.encode("sak"));
        sak.addUserAuthorities("READ");

        User esra = new User("esra",this.passwordEncoder.encode("esra"));
        esra.addUserAuthorities("READ");
        esra.addUserAuthorities("WRITE");

        userRepository.save(sak);
        userRepository.save(esra);
    }
}
