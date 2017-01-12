package north.sample;

import com.avaje.ebean.Ebean;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import north.sample.domain.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookTest extends BaseEbeanTest {

    @Before
    public void importData() throws URISyntaxException, IOException {
        importData("testdata/data.sql");
    }

    @Test
    public void firstPageTest() {
        List<Book> books = Ebean.find(Book.class).findList();
        Assert.assertEquals(3, books.size());
    }

}
