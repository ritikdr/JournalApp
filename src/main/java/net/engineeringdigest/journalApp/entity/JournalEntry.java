package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.annotation.Id;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
//
//     public LocalDateTime getDate() {
//         return date;
//     }
//
//     public void setDate(LocalDateTime date) {
//         this.date = date;
//     }
//
//     @Id
//     public ObjectId getId() {
//         return id;
//     }
//     public void setId(ObjectId id) {
//         this.id = id;
//     }
//
//     public String getTitle() {
//         return title;
//     }
//     public void setTitle(String title) {
//         this.title = title;
//     }
//
//     public String getContent() {
//         return content;
//     }
//     public void setContent(String content) {
//         this.content = content;
//     }
}
