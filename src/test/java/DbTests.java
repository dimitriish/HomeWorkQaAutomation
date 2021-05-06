import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.DbService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


public class DbTests {
    @BeforeAll
    static void beforeAll() {
        BeforeUtils.createData();
    }

    @Test
    public void animalContains10Rows() {
        ResultSet resultSet = DbService.executeQuery("select * from public.animal");
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assertions.assertEquals(size, 10);
    }

    @Test
    public void notAddingRowWithIndexFrom1To10IntoAnimal() {
        int result = DbService
                .executeUpdate("insert into public.animal (id, \"name\", age, \"type\", sex, place)"
                        + "values (5, 'anton', 18, 1, 0, 0)");
        Assertions.assertEquals(result, 0);
    }

    @Test
    public void notAddingRowWithNullNameIntoWorkman() {
        int result = DbService
                .executeUpdate("insert into public.workman (id, \"name\")"
                        + "values (7, null)");
        Assertions.assertEquals(result, 0);
    }

    @Test
    public void numOfRows6AfterAddingRowIntoPlaces() {
        int result = DbService
                .executeUpdate("insert into public.places (id, \"row\", place_num, \"name\") "
                        + "values(10, 1, 185, 'Загон 10')");
        Assertions.assertEquals(result, 1);
        ResultSet resultSet = DbService.executeQuery("select * from public.places");
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assertions.assertEquals(size, 6);
    }

    @Test
    public void only3RowsInZoo() throws SQLException {
        ResultSet rs = DbService.executeQuery("select * from public.zoo");
        HashSet<String> actual = new HashSet<>();
        while (rs.next()) {
            actual.add(rs.getString(2));
        }
        HashSet<String> expected = new HashSet<>();
        expected.add("Центральный");
        expected.add("Северный");
        expected.add("Западный");
        Assertions.assertIterableEquals(actual, expected);


    }
}
