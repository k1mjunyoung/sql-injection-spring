package sqlinjectionspring.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {

    @Id
    @NonNull
    @Column(length = 128, unique = true)
    private String id;

    @NonNull
    private String password;

    @NonNull
    private String type = "member";

    @NonNull
    @Column(length = 128, unique = true)
    private String name;

    @NonNull
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Builder.Default
    private LocalDateTime registrationDateTime = LocalDateTime.now();
}
