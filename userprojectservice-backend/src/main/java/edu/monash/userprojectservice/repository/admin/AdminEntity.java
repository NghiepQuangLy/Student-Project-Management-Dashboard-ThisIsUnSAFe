package edu.monash.userprojectservice.repository.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ADMINS", schema = "SPMD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity implements Serializable {

    @Id
    @Column(name = "admin_email_address")
    private String emailAddress;

    @Column(name = "admin_given_name")
    private String admingGivenName;

    @Column(name = "admin_family_name")
    private String adminFamilyName;

}
