package ru.tweekyone.dirs.exceptions;

public class NoSuchDirectoryException extends RuntimeException{
    public NoSuchDirectoryException(String path) {
        super(String.format("Invalid path \"%s\"", path));
    }
}
