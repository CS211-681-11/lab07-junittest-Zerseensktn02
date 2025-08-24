package ku.cs.services;

import ku.cs.models.StudentList;
import ku.cs.models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentHardCodeDatasourceTest {

    @Test
    @DisplayName("ไม่ควรคืนค่า null")
    void testReadDataNull() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();

        StudentList list = ds.readData();

        assertNotNull(list, "readData() ต้องไม่เป็น null");
    }

    @Test
    @DisplayName("ควรมีนักเรียนทั้งหมด 4 คน")
    void testReadDataFourStudents() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();

        StudentList list = ds.readData();

        assertNotNull(list);
        assertNotNull(list.getStudents(), "getStudents() ต้องไม่เป็น null");
        assertEquals(4, list.getStudents().size(), "ควรมีนักเรียน 4 คนตามที่ hard-code");
    }

    @Test
    @DisplayName("ข้อมูลนักเรียนต้องตรงกับ id และชื่อที่hard-codeไว้")
    void readData_shouldMatchExpectedIdsAndNames() {
        StudentHardCodeDatasource ds = new StudentHardCodeDatasource();
        StudentList list = ds.readData();

        // expected
        Map<String, String> expected = new HashMap<>();
        expected.put("6710400001", "First");
        expected.put("6710400002", "Second");
        expected.put("6710400003", "Third");
        expected.put("6710400004", "Fourth");

        assertEquals(4, list.getStudents().size());

        for (Student s : list.getStudents()) {
            String id = s.getId();
            String name = s.getName();

            assertTrue(expected.containsKey(id), "เจอ id ที่ไม่คาดคิด: " + id);
            assertEquals(expected.get(id), name, "ชื่อไม่ตรงกับ id: " + id);
        }
    }
}
