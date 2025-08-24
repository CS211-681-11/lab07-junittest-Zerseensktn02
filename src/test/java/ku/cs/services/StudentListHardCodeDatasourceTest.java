package ku.cs.services;

import ku.cs.models.StudentList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentListHardCodeDatasourceTest {

    @Test
    @DisplayName("readData() ต้องคืน StudentListที่ไม่เป็นnullและมีข้อมูลอยู่")
    void testReadData() {
        StudentListHardCodeDatasource ds = new StudentListHardCodeDatasource();

        StudentList list = ds.readData();

        assertNotNull(list, "readData() ต้องไม่เป็น null");
        assertNotNull(list.getStudents(), "getStudents() ต้องไม่เป็น null");
        assertTrue(list.getStudents().size() > 0, "ควรมีนักเรียนอย่างน้อย 1 คน");
    }

    @Test
    @DisplayName("writeData() เรียกได้โดยไม่ควรเกิด Exception")
    void testWriteData() {
        StudentListHardCodeDatasource ds = new StudentListHardCodeDatasource();
        StudentList dummy = new StudentList();
        dummy.addNewStudent("999", "Kantinant");

        assertDoesNotThrow(() -> ds.writeData(dummy),
                "writeData() ไม่ควร throw exception แม้จะไม่ได้ทำอะไร");
    }
}
