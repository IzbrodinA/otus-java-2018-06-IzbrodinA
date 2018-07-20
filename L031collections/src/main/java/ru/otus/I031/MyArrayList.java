package ru.otus.I031;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] m ;
    private int size ;

    public MyArrayList(T size) {
        m = (T[])new Object[(int)size];
        this.size = (int)size;
    }
    public MyArrayList() {
        m = (T[])new Object[1];
        size = 0;
    }

    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        if (true) {throw new RuntimeException();}
        return null;
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[])new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (true) {throw new RuntimeException();}
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
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (true) {throw new RuntimeException();}
        return false;
    }

    @Override
    public void clear() {
        if (true) {throw new RuntimeException();}
    }

    @Override
    public T get(int index) {
        if (index <0 || index >= size) throw new IndexOutOfBoundsException();
        return m[index];
    }

    @Override
    public T set(int index, T element) {
        m[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (true) {throw new RuntimeException();}
    }

    @Override
    public T remove(int index) {
        if (true) {throw new RuntimeException();}
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (true) {throw new RuntimeException();}
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (true) {throw new RuntimeException();}
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {

        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (true) {throw new RuntimeException();}
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (true) {throw new RuntimeException();}
        return null;
    }

    private class ElementsIterator implements ListIterator<T> {

        private static final int LAST_IS_NOT_SET = -1;
        private int index;
        private int lastIndex = LAST_IS_NOT_SET;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            if (index > 0 && index < MyArrayList.this.size){
                throw new IndexOutOfBoundsException();
            }
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            return MyArrayList.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastIndex = index++;//or lastIndex = nextIndex(); index++;
            return MyArrayList.this.m[lastIndex];
        }

        @Override
        // . 1 . 2 . 8 ^ 3 . 4. 5
        public void add(final T element) {
            // BEGIN (write your solution here)
            MyArrayList.this.add(index, element);
            lastIndex = LAST_IS_NOT_SET;
            index++;

            // END
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (lastIndex != -1 ) {
                MyArrayList.this.set(lastIndex, element);
                lastIndex = LAST_IS_NOT_SET;
            }else
                throw new IllegalStateException();
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)
            return index - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            return  index;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            return index != 0;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (hasPrevious()){
                index = lastIndex = previousIndex();
                return get(index);
            }else
                throw new NoSuchElementException();
            // END
        }

        @Override
        public void remove() {
            if (lastIndex == LAST_IS_NOT_SET) throw new IllegalStateException();
            MyArrayList.this.remove(lastIndex);
            index--;
            lastIndex = LAST_IS_NOT_SET;
        }
    }


}
