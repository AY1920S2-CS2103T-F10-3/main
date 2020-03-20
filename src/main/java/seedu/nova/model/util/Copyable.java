package seedu.nova.model.util;

/**
 * Able to be shallow copied
 * @param <T> object
 */
public interface Copyable<T> {
    public T getCopy();
}
