package airplane.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Embeddable
public class Airplane {

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "schedule")
    private List<Seat> seatList;

    public Airplane(int capacity) {
        this.capacity = capacity;
    }
}
