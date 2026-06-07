package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import  net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntry().add(saved);
            user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.print(e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean remove = false;
        try {
            User user = userService.findByUserName(userName);
            remove = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
            if (remove) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch(Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return remove;
    }
}


// controller ---> service --->  repository