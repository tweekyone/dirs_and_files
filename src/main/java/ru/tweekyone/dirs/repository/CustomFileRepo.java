package ru.tweekyone.dirs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tweekyone.dirs.entity.CustomFile;

@Repository
public interface CustomFileRepo extends JpaRepository<CustomFile, Long> {
}
