package ru.updateyoureng.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    private String phone;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserWord> vocabulary;
    @JsonIgnore
    private String rawProperties;

    @JsonValue
    public Map<String, Object> getProperties() throws IOException {
        return new ObjectMapper().readValue(this.getRawProperties(), new TypeReference<Map<String, Object>>() {
        });
    }

    public void setProperties(Map<String, Object> props) throws JsonProcessingException {
        this.setRawProperties(new ObjectMapper().writeValueAsString(props));
    }

}
