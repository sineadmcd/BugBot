package bookstore;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public class Book {
    
    private @Nonnull String author;
    private @Nonnull String title;
    private @CheckForNull String subtitle;

    public @Nonnull String getAuthor() {
        return author;
    }

    public @Nonnull String getSubtitle() {
        return subtitle;
    }

    public @Nonnull String getTitle() {
        return title;
    }

}
