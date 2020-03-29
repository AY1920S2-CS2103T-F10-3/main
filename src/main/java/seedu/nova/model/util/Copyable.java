package seedu.nova.model.util;

/**
 * Objects that can be shallow copied
 * @param <T> the object
 */
public interface Copyable<T> {
    public T getCopy();
}
