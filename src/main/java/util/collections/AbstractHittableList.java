package util.collections;

import util.collections.HittableList;
import util.Hittable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractHittableList implements HittableList {
    private List<Hittable> list;

    public AbstractHittableList(List<Hittable> list) {
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
    public Iterator<Hittable> iterator() {
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
    public boolean add(Hittable hittable) {
        return list.add(hittable);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends Hittable> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Hittable> c) {
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
    public Hittable get(int index) {
        return list.get(index);
    }

    @Override
    public Hittable set(int index, Hittable element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Hittable element) {
        list.add(index, element);
    }

    @Override
    public Hittable remove(int index) {
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
    public ListIterator<Hittable> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Hittable> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Hittable> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}
