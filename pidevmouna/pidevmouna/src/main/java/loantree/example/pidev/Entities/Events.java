package loantree.example.pidev.Entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString


public class Events implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Event;

    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String venue;
    private String description;
    private String image;
    private String organizer;
    private Double ticketPrice;
    private String ticketAvailability;
    private String speakers;
    private String sponsors;
    private String contactInformation;
    private String socialMediaLinks;
    private String tags;
    private String registrationInformation;
    private String agenda;

    @ManyToMany(mappedBy = "events")
    private List<User> users;

    public Events() {

    }
}
