package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class MyList<T extends Number> implements Iterable<T> {
    private Object[] array;
    private int size;
    private static final int CAPACITY = 10;

    public MyList() {
        array = new Object[CAPACITY];
        size = 0;
    }

    public void add(T element) {
        resize();
        array[size++] = element;
        if (size == array.length) {
            resize();
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    private void resize() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    public MyList<T> map(Function<T, T> f) {
        MyList<T> mappedList = new MyList<>();
        for (int i = 0; i < size; i++) {
            T result = f.apply((T) array[i]);
            mappedList.add(result);
        }
        return mappedList;
    }

    public int size() {
        return size;
    }

    /**
     *  * Inner class has access to all fields of an outer class.
     *  * We can create it within the outer class.
     *  *
     *  * Inner static class has access to static fields of the outer class.
     *  * We can create it without an outer class object.
     */

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }
    private class MyListIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[index++];
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MyList<T> otherList = (MyList<T>) obj;
        if (size != otherList.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!array[i].equals(otherList.array[i])) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = 31 * result + array[i].hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}