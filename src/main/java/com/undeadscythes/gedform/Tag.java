package com.undeadscythes.gedform;

/**
 * A {@link Tag} defines the data that it is connected to.
 *
 * @author UndeadScythes
 */
public interface Tag {
    /**
     *
     * @return The literal representation of a {@link Tag}
     */
    String getTag();

    /**
     *
     * @return A user friendly description of this tag
     */
    String getFriendly();

    /**
     *
     * @return The type of data this tag pertains to
     */
    TagType getType();
}
