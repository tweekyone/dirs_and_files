package ru.tweekyone.dirs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tweekyone.dirs.entity.Directory;

@Repository
public interface DirectoryRepo extends JpaRepository<Directory, Long> {
}
