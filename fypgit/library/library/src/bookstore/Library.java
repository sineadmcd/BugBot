package bookstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nonnull;

public class Library {

    private final Set<Book> books = new TreeSet<Book>(new ComparatorImpl());
    
    public void addBook(@Nonnull Book newBook) {
        if (newBook == null) {
            //@Nonnull was not present in the first version, protect against old
            //clients which may not know about the constraint
            return;
        }
        
        books.add(newBook);
    }
    
    public @Nonnull Iterable<? extends String> describeBooksBy(@Nonnull String author) {
        List<String> result = new ArrayList<String>();
        
        for (Book b : books) {
            if (!author.equals(b.getAuthor())) continue;
            
            result.add(String.format("%s: %d", b.getAuthor(), b.getTitle()));
        }
        
        return result;
    }
    
    private static class ComparatorImpl implements Comparator<Book>, Serializable {

        @Override public int compare(Book o1, Book o2) {
            int r = o1.getAuthor().compareTo(o2.getAuthor());
            
            if (r != 0) return r;
            
            r = o1.getTitle().compareTo(o2.getTitle());
            
            if (r != 0) return r;
            
            r = o1.getSubtitle().compareTo(o2.getSubtitle());
            
            return 0;
        }
        
        private static final long serialVersionUID = 0L;
    }
    
}
