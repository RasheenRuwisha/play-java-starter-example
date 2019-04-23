import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.LibraryManagerDao;
import dao.LibraryManagerDaoImpl;
import org.junit.Before;
import services.LibraryManager;
import services.WestminsterLibraryManager;

public class TestBase {
    protected Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            bind(LibraryManagerDao.class).to(LibraryManagerDaoImpl.class);
            bind(LibraryManager.class).to(WestminsterLibraryManager.class);
        }
    });

    @Before
    public void setup() {
        injector.injectMembers(this);
    }
}
