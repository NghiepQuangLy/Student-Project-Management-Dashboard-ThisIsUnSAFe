package edu.monash.userprojectservice.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Users", schema = "SPMD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "user_group")
    private String userGroup;

    @Override
    public String toString() {
        return String.format(
                "Users[email_address=%s, family_name='%s', given_name='%s', user_group='%s']",
                emailAddress, familyName, givenName, userGroup);
    }
}
