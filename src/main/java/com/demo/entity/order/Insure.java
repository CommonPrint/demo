package com.demo.entity.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Insure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeName;

    @Column(length = 2000)
    private String description;

    private Integer price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "insure_properties",
            joinColumns = @JoinColumn(name = "insure_id"),
            inverseJoinColumns = @JoinColumn(name = "insure_property_id"))
    private Set<InsureProperty> properties = new HashSet<>();

}
