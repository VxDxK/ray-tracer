package util.collections;

import objects.Boundable;

import java.util.*;

public abstract class AbstractBoundableList implements BoundableList {
    protected List<Boundable> list;

    public AbstractBoundableList(List<Boundable> list) {
        this.list = list;
    }


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Boundable> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Boundable boundable) {
        return list.add(boundable);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(list).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Boundable> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Boundable> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Boundable get(int index) {
        return list.get(index);
    }

    @Override
    public Boundable set(int index, Boundable element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Boundable element) {
        list.add(index, element);
    }

    @Override
    public Boundable remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Boundable> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Boundable> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Boundable> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

}
