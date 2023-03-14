package loantree.example.pidev.Entities;



import javax.persistence.*;
@Entity
@Table(name = "role")
public class Role {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private Roles name;

        public Role() {

        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Roles getName() {
                return name;
        }

        public void setName(Roles name) {
                this.name = name;
        }

        public Role(Roles name) {
            this.name = name;
        }

    }
