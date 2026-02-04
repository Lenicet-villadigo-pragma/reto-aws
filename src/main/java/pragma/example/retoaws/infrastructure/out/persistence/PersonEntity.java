package pragma.example.retoaws.infrastructure.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    private String idNumber;
    private String name;
    private String email;

}