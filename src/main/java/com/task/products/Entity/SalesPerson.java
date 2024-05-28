package com.task.products.Entity;

import com.task.products.Utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SalesPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "salesPersonIncharge")
    private List<Orders> orders;

    @Enumerated(EnumType.STRING)
    private Role role;
}
