package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    private StudentList list;

    @BeforeEach
    void setUp() {
        list = new StudentList();
    }

    @Test
    @DisplayName("เพิ่มเลขidเเละชื่อนักเรียน ถ้าถูกต้องข้อมูลต้องถูกเพิ่ม")
    void testAddNewStudent() {
        list.addNewStudent("6710405265", "Kantinant");
        assertEquals(1, list.getStudents().size());
        assertNotNull(list.findStudentById("6710405265"));
    }

    @Test
    @DisplayName("Trim ข้อมูลที่รับมาid/name ถ้ามีช่องว่างควรตัดแล้วเพิ่มได้")
    void testAddNewStudentTrim() {
        list.addNewStudent("  6710405265  ", "  Kantinant  ");
        assertNotNull(list.findStudentById("6710405265"));
        assertEquals(1, list.getStudents().size());
    }

    @Test
    @DisplayName("ถ้าsearchไม่เจอfindStudentByIdต้องคืนค่า null")
    void testFindStudentById() {
        assertNull(list.findStudentById("6710405265"));
    }

    @Test
    @DisplayName("กรองชื่อด้วย 'Pu' ควรได้เฉพาะ 001:Pup (ขนาด 1)")
    void testFilterByName() {
        list.addNewStudent("001", "Pup");
        list.addNewStudent("002", "Bruno");
        list.addNewStudent("003", "Nick");
        StudentList filtered = list.filterByName("Pu");

        assertEquals(1, filtered.getStudents().size());
        assertNotNull(filtered.findStudentById("001"));
        assertNull(filtered.findStudentById("002"));
        assertNull(filtered.findStudentById("003"));
    }

    @Test
    @DisplayName("giveScoreToIdพบ id บวกคะแนนและขนาดลิสต์เดิม")
    void testGiveScoreToId_found() {
        list.addNewStudent("001", "Pup", 50.0);
        list.giveScoreToId("001", 10.0);
        assertEquals(1, list.getStudents().size());
        assertNotNull(list.findStudentById("001"));
    }

    @Test
    @DisplayName("viewGradeOfId: id ไม่พบต้องคืนค่า null")
    void testViewGradeOfId() {
        assertNull(list.viewGradeOfId("4321"));
    }

    @Test
    @DisplayName("getStudents เพิ่ม 2 ข้อมูล ขนาดเป็น 2 ค้นเจอทั้งคู่")
    void testGetStudents() {
        list.addNewStudent("001", "Pup");
        list.addNewStudent("002", "Joe");
        assertEquals(2, list.getStudents().size());
        assertNotNull(list.findStudentById("001"));
        assertNotNull(list.findStudentById("002"));
    }
}
