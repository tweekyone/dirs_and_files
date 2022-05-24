package ru.tweekyone.dirs.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameChunk implements Comparable<NameChunk> {
    private Integer integer;
    private Character character;

    public NameChunk(Integer integer) {
        this.integer = integer;
        this.character = null;
    }

    public NameChunk(Character character) {
        this.integer = null;
        this.character = character;
    }

    public boolean isNumeric() {
        return integer != null;
    }

    @Override
    public int compareTo(NameChunk nameChunk) {
        if (this.isNumeric() && nameChunk.isNumeric()) {
            return this.integer.compareTo(nameChunk.getInteger());
        } else if (!this.isNumeric() && !nameChunk.isNumeric()) {
            return this.character.compareTo(nameChunk.getCharacter());
        } else if (this.isNumeric() && !nameChunk.isNumeric()) {
            if(nameChunk.character == '.') {
                return 1;
            } else {
                return -1;
            }
        } else {
            if(this.character == '.') {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
