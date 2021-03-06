package Home20;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMapTestJUnit5 {
    private MyMap<String, Integer> map;


    @BeforeEach
    void setup() {
        map = new MyMap<>();
        map.put("google.com", 123);
        map.put("youtube.com", 173);
        map.put("google.com/3", 1);
        map.put("google.com/4", 2);
        map.put("google.com/5", 3);
        map.put("google.com/6", 4);
        map.put("google.com/9", 7);
        map.put("google.com/10", 9);
        map.put("google.com/11", 10);
    }


    @Nested
    class sizeTests {

        @Test
        void sizeTest() {
            Assertions.assertEquals(9, map.size());
        }

        @Test
        void sizeAfterPutValue() {
            map.put("netflix.com", 547714319);
            Assertions.assertEquals(10, map.size());
        }

        @Test
        void sizeAfterRemoveValue() {
            map.remove("google.com/6");
            Assertions.assertEquals(8, map.size());
        }

    }


    @ParameterizedTest
    @CsvSource({"google.com, 123", "youtube.com, 173", "google.com/3, 1", "google.com/4, 2", "google.com/5, 3",
            "google.com/6, 4", "google.com/9, 7", "google.com/10, 9", "google.com/11, 10"})
    void getValueTest(String key, int value) {
        Assertions.assertEquals(value, map.get(key));
        Assertions.assertNull(map.get(null));
        Assertions.assertNull(map.get("Facebook"));

    }


    @Test
    void isEmptyTest() {
        Assertions.assertFalse(map.isEmpty());
    }

    @Test
    void isEmptyTestIfClearTest() {
        map.clear();
        Assertions.assertTrue(map.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {123, 173, 1, 2, 3, 4, 7, 9, 10})
    void containsValueTest(int value) {
        Assertions.assertTrue(map.containsValue(value));
    }


    @Test
    void containsValueThatIsNot() {
        Assertions.assertFalse(map.containsValue(1768));
    }

    @ParameterizedTest
    @ValueSource(strings = {"google.com", "youtube.com", "google.com/3", "google.com/4", "google.com/5",
            "google.com/6", "google.com/9", "google.com/10", "google.com/11"})
    void containsKeyTest(String key) {
        Assertions.assertTrue(map.containsKey(key));
        Assertions.assertFalse(map.containsKey(null));
    }

    @Test
    void containsKeyThatIsNot() {
        Assertions.assertFalse(map.containsKey("next"));
    }

    @Test
    void putOneValueTest() {
        map.put("Netflix", 547714319);
        Assertions.assertTrue(map.containsKey("Netflix"));
        Assertions.assertNull(map.put(null,12));
    }

    @Test
    void removeTest() {
        Assertions.assertEquals(123, map.remove("google.com"));
        Assertions.assertNull(map.remove(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"oracle.com", "netflix.com"})
    void putAllTest(String key) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("oracle.com", 1372541205);
        hashMap.put("netflix.com", 547714319);
        map.putAll(hashMap);
        Assertions.assertTrue(map.containsKey(key));
    }

    @Test
    void clearTest() {
        map.clear();
        Assertions.assertEquals(0, map.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"google.com", "youtube.com", "google.com/3", "google.com/4", "google.com/5",
            "google.com/6", "google.com/9", "google.com/10", "google.com/11"})
    void keySetTest(String key) {
        Set<String> keys = map.keySet();
        Assertions.assertTrue(keys.contains(key));

    }

    @ParameterizedTest
    @ValueSource(ints = {123, 173, 1, 2, 3, 4, 7, 9, 10})
    void valuesTest(int value) {
        Collection<Integer> values = map.values();
        Assertions.assertEquals(values, map.values());
        Assertions.assertTrue(values.containsAll(map.values()));
        Assertions.assertTrue(values.contains(value));
    }

    @Test
    void entrySetTest() {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Assertions.assertEquals(entries, map.entrySet());
        Assertions.assertEquals(9, map.entrySet().size());
    }

}