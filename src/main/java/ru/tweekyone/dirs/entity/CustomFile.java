package ru.tweekyone.dirs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file")
public class CustomFile {

    @Id
    @SequenceGenerator(name = "file_id_seq", sequenceName = "file_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_seq")
    private Long id;

    @Column(name = "is_file")
    private Boolean isFile;

    @Column(name = "size")
    private Long size;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;
}
