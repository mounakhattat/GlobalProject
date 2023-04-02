package loantree.example.pidev.Entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "roles")

public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idRole;

        @Enumerated(EnumType.STRING)
        private ERole name;

        public Role() {

        }

        public Integer getIdRole() {
                return idRole;
        }

        public void setIdRole(Integer idRole) {
                this.idRole = idRole;
        }

        public ERole getName() {
                return name;
        }

        public void setName(ERole name) {
                this.name = name;
        }
}