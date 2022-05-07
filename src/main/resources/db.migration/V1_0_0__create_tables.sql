CREATE SEQUENCE directory_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE file_id_seq START 1 INCREMENT 1;

CREATE TABLE Directory (
                           directory_id BIGINT NOT NULL ,
                           date TIMESTAMP NOT NULL ,
                           path VARCHAR(255) NOT NULL ,
                           PRIMARY KEY (directory_id)
);

CREATE TABLE File (
                      file_id BIGINT NOT NULL ,
                      directory_id BIGINT NOT NULL ,
                      is_file BOOLEAN NOT NULL ,
                      size BIGINT ,
                      PRIMARY KEY (file_id) ,
                      CONSTRAINT fk_directory FOREIGN KEY (directory_id)
                          REFERENCES Directory(directory_id)
)