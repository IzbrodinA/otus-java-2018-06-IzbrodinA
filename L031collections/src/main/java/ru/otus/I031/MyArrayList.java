package ru.otus.I031;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] m;
    private int size;

    public MyArrayList(T size) {
        m = (T[]) new Object[(int) size];
        this.size = (int) size;
    }

    public MyArrayList() {
        m = (T[]) new Object[1];
        size = 0;
    }

    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        if (true) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[]) new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (true) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public boolean add(T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;

    }

    @Override
    public boolean remove(Object o) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (true) {
            throw new RuntimeException();
        }
        return false;
    }

    @Override
    public void clear() {
        if (true) {
            throw new RuntimeException();
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return m[index];
    }

    @Override
    public T set(int index, T element) {
        m[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (true) {
            throw new RuntimeException();
        }
    }

    @Override
    public T remove(int index) {
        if (true) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (true) {
            throw new RuntimeException();
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (true) {
            throw new RuntimeException();
        }
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {

        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (true) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (true) {
            throw new RuntimeException();
        }
        return null;
    }

    private class ElementsIterator implements ListIterator<T> {

        private static final int LAST_IS_NOT_SET = -1;
        private int index;
        private int lastIndex = -1;


        public ElementsIterator(final int index) {
            if (index > 0 && index < MyArrayList.this.size) {
                throw new IndexOutOfBoundsException();
            }
            this.index = index;

        }

        @Override
        public boolean hasNext() {
            return MyArrayList.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastIndex = index++;
            return MyArrayList.this.m[lastIndex];

        }

        @Override
        public boolean hasPrevious() {
            if (true) {
                throw new RuntimeException();
            }
            return false;
        }

        @Override
        public T previous() {
            if (true) {
                throw new RuntimeException();
            }
            return null;
        }

        @Override
        public int nextIndex() {
            if (true) {
                throw new RuntimeException();
            }
            return 0;
        }

        @Override
        public int previousIndex() {
            if (true) {
                throw new RuntimeException();
            }
            return 0;
        }

        @Override
        public void remove() {
            if (true) {
                throw new RuntimeException();
            }
        }

        @Override
        public void set(T t) {
            if (lastIndex != LAST_IS_NOT_SET) {
                MyArrayList.this.set(lastIndex, t);
                lastIndex = LAST_IS_NOT_SET;
            } else
                throw new IllegalStateException();
        }

        @Override
        public void add(T t) {
            if (true) {
                throw new RuntimeException();
            }
        }
    }
}
