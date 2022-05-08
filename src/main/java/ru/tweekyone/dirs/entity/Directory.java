package ru.tweekyone.dirs.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "directory")
public class Directory {

    @Id
    @Column(name = "directory_id")
    @SequenceGenerator(name = "directory_id_seq", sequenceName = "directory_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directory_id_seq")
    private Long id;

    @Column(name = "date")
    private LocalDateTime dateTime;

    @Column(name = "path")
    private String path;

    @OneToMany(mappedBy = "directory", fetch = FetchType.EAGER)
    private List<CustomFile> customFiles;
}
