package hcmute.edu.vn.foodoapp;

import org.junit.Test;

import static org.junit.Assert.*;

import hcmute.edu.vn.foodoapp.model.Food;
import hcmute.edu.vn.foodoapp.service.FoodService;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSearch(String keyword) {
        FoodService foodService = new FoodService();
        int length = foodService.getByKeyword("Combo").size();
        assertNotEquals(0, length);
    }
}