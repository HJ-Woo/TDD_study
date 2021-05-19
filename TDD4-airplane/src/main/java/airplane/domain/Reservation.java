package airplane.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Seat seat;

    @Column(nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.RESERVED;

    //연관관계 편의 메소드
    public void setUser(User user) {
        if(this.user != null) {
            this.user.getReservationList().remove(this);
        }
        this.user = user;
        user.getReservationList().add(this);
    }

    public void cancel() {
        status = ReservationStatus.CANCELED;
    }

    @Builder(builderMethodName = "defaultBuilder", builderClassName = "defaultBuilder")
    public Reservation(Schedule schedule, User user, Seat seat, Reservation reservation) {
        this.schedule = schedule;
        this.user = user;
        this.seat = seat;
        this.price = reservation.price;
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Reservation(int price) {
        this.price = price;
    }
}
