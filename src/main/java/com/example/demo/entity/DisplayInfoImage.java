package com.example.demo.entity;

import lombok.*;
import org.hibernate.metamodel.model.domain.IdentifiableDomainType;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class DisplayInfoImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="display_info_id")
    private DisplayInfo displayInfo;

    @ManyToOne
    @JoinColumn(name="file_info_id")
    private FileInfo fileInfo;
}