package me.tecc.tajapi.tech;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BitSequence implements Serializable, Comparable<BitSequence>, Iterable<Boolean> {

    private static final long serialVersionUID = -71016200107100010L;

    private byte value;

    public BitSequence(byte value) {
        this.value = value;

    }

    public BitSequence shiftLeft(int amount) {
        return new BitSequence((byte) (value << amount));
    }
    public BitSequence shiftRight(int amount) {
        return new BitSequence((byte) (value >> amount));
    }

    public BitSequence concat(byte value) {
        BitSequence o = new BitSequence(this.value);
        Byte b = value;
        o.shiftLeft(b.);
    }

    public void setBit(int pos, int val) {
        return val
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSequence that = (BitSequence) o;
        return value == that.value;
    }

    /**
     * Converts this {@code BitSequence} to
     * @return the BitSequence as a boolean array
     */

    public boolean[] toBooleanArray() {
        boolean[] boolArr = new boolean[8];

        for(int i = 0; i < 8; i++)
            boolArr[i] = (this.value & (byte) (128 / Math.pow(2, i)) ) != 0;
        return boolArr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(BitSequence o) {
        if (this.equals(o))
            return 0;
        return this.value > o.value ? 1 : -1;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Iterator<Boolean>() {
            boolean[] val = BitSequence.this.toBooleanArray();
            int index = -1;
            @Override
            public boolean hasNext() {
                return (index + 1) < val.length;
            }

            @Override
            public Boolean next() {
                index++;
                return val[index];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Boolean> action) {
        for (Boolean bool : this) {
            action.accept(bool);
        }
    }

    @Override
    public Spliterator<Boolean> spliterator() {
        return null;
    }
}
