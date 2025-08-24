package ku.cs.services;

import ku.cs.models.Student;
import ku.cs.models.StudentList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StudentListFileDatasourceTest {

    @Test
    @DisplayName("constructorสร้างไฟล์/โฟลเดอร์ได้เอง")
    void testStudentListFileDatasource() {
        String dir = "testdata";
        String file = "students.csv";

        File f = new File(dir + File.separator + file);
        if (f.exists()) {
            f.delete();
        }
        File d = new File(dir);
        if (d.exists()) {
            d.delete();
        }

        StudentListFileDatasource ds = new StudentListFileDatasource(dir, file);

        assertTrue(new File(dir).exists(), "โฟลเดอร์ต้องถูกสร้างขึ้น");
        assertTrue(new File(dir + File.separator + file).exists(), "ควรมีไฟล์ถูกสร้างขึ้น");
    }

    @Test
    @DisplayName("readData()ควรได้StudentList ว่าง")
    void testReadData() {
        String dir = "testdata";
        String file = "empty.csv";

        StudentListFileDatasource ds = new StudentListFileDatasource(dir, file);
        StudentList list = ds.readData();

        assertNotNull(list);
        assertEquals(0, list.getStudents().size());
    }

    @Test
    @DisplayName("writeData()กับreadData()ต้องได้ข้อมูลตรงกัน")
    void writeThenRead_shouldReturnSameData() {
        String dir = "testdata";
        String file = "students.csv";
        StudentListFileDatasource ds = new StudentListFileDatasource(dir, file);

        StudentList toWrite = new StudentList();
        toWrite.addNewStudent("6710400001", "First", 10.0);
        toWrite.addNewStudent("6710400002", "Second", 20.5);

        ds.writeData(toWrite);

        StudentList readBack = ds.readData();

        assertEquals(2, readBack.getStudents().size());

        Student s1 = readBack.findStudentById("6710400001");
        assertNotNull(s1);
        assertEquals("First", s1.getName());
        assertEquals(10.0, s1.getScore());

        Student s2 = readBack.findStudentById("6710400002");
        assertNotNull(s2);
        assertEquals("Second", s2.getName());
        assertEquals(20.5, s2.getScore());
    }
}
