package edu.uprm.ece.icom4035.mygrades.testers;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.uprm.ece.icom4035.mygrades.managers.Student;
import edu.uprm.ece.icom4035.mygrades.managers.StudentManager;

public class Test4 {
	private StudentManager studentManager;
	
	Student Ron = new Student("Ron", "Mos");
	Student Amy = new Student("Amy", "New");
	Student Apu = new Student("Apu", "Ace");
	Student Ned = new Student("Ned", "Joe");
	Student Xi = new Student("Xi", "Lee");
	Student Bob = new Student("Bob", "Goo");
	Student Cal = new Student("Cal", "Wes");
	
	Student Al = new Student("Al", "Abe");
	Student Zen = new Student("Zen", "Wiz");
	Student Jil = new Student("Jil", "Old");
	Student Gen = new Student("Gen", "Fly");
	Student Joe = new Student("Joe", "Tod");
	Student Abi = new Student("Abi", "Aba");
	Student Abu = new Student("Abu", "Abu");
	Student Yu = new Student("Yu", "Yo");
	

	@Before
	public void setUp() throws Exception {
		this.studentManager = new StudentManager();
		
		this.studentManager.addStudent(Ron);
		this.studentManager.addStudent(Amy);
		this.studentManager.addStudent(Apu);
		this.studentManager.addStudent(Ned);
		this.studentManager.addStudent(Xi);
		this.studentManager.addStudent(Bob);
		this.studentManager.addStudent(Cal);
		
	}

	@Test
	public void testAdd() {
		
		this.studentManager.addStudent(Al);
		this.studentManager.deleteStudent(Al);
		this.studentManager.addStudent(Al);
		assertTrue(this.studentManager.getAll().get(0).equals(Al));
		this.studentManager.deleteStudent(Al);
		assertFalse(this.studentManager.getAll().get(0).equals(Al));
		this.studentManager.addStudent(Zen);
		assertTrue(this.studentManager.getAll().last().equals(Zen));
	}

	@Test
	public void testSize() {
		
		assertTrue(this.studentManager.getAll().size() == 7);
		this.studentManager.addStudent(Ned);
		this.studentManager.addStudent(Jil);
		this.studentManager.deleteStudent(Xi);
		assertTrue(this.studentManager.getAll().size() == 8);
		this.studentManager.clearStudents();
		assertTrue(this.studentManager.getAll().size() == 0);
		this.studentManager.addStudent(Ron);
		assertTrue(this.studentManager.getAll().size() == 1);
	}

	@Test
	public void testRemoveE() {
		this.studentManager.deleteStudent(Ron);
		this.studentManager.addStudent(Ron);
		this.studentManager.deleteStudent(Ron);
		assertFalse(this.studentManager.getAll().contains(Ron));
		this.studentManager.deleteStudent(Xi);
		assertFalse(this.studentManager.getAll().contains(Xi));
		assertFalse(this.studentManager.deleteStudent(Gen));
		this.studentManager.clearStudents();
		assertFalse(this.studentManager.deleteStudent(Gen));
		this.studentManager.addStudent(Joe);
		assertTrue(this.studentManager.deleteStudent(Joe));
	}

	@Test
	public void testRemoveInt() {
		this.studentManager.deleteStudent(1);
		assertFalse(this.studentManager.getAll().contains(Bob));
		this.studentManager.deleteStudent(0);
		this.studentManager.addStudent(Apu);
		this.studentManager.addStudent(Apu);
		this.studentManager.addStudent(Apu);
		this.studentManager.deleteStudent(0);
		this.studentManager.deleteStudent(0);
		this.studentManager.deleteStudent(0);		
		assertFalse(this.studentManager.getAll().contains(Apu));
		this.studentManager.addStudent(Apu);
		assertTrue(this.studentManager.deleteStudent(1));

	}

	@Test
	public void testRemoveAll() {
		assertTrue(this.studentManager.getAll().size() !=0);
		this.studentManager.clearStudents();
		assertTrue(this.studentManager.getAll().size() == 0);
		this.studentManager.addStudent(Joe);
		this.studentManager.addStudent(Ned);
		this.studentManager.addStudent(Ned);
		this.studentManager.addStudent(Ned);
		this.studentManager.addStudent(Ned);
		assertTrue(this.studentManager.getAll().removeAll(Ned) == 4);
		assertTrue(this.studentManager.getAll().size() == 1);
	}

	@Test
	public void testFirst() {
		assertTrue(this.studentManager.getAll().first().equals(Apu));
		this.studentManager.addStudent(Al);
		assertTrue(this.studentManager.getAll().first().equals(Al));
		this.studentManager.addStudent(Abu);
		this.studentManager.addStudent(Abi);
		assertTrue(this.studentManager.getAll().first().equals(Abi));
	}

	@Test
	public void testLast() {
		assertTrue(this.studentManager.getAll().last().equals(Cal));
		this.studentManager.addStudent(Zen);
		assertTrue(this.studentManager.getAll().last().equals(Zen));
		this.studentManager.deleteStudent(Zen);
		this.studentManager.addStudent(Zen);
		assertTrue(this.studentManager.getAll().last().equals(Zen));
	
	}
 
	@Test
	public void testGet() {
		assertTrue(this.studentManager.getAll().get(1).equals(Bob));
		assertTrue(this.studentManager.getAll().get(0).equals(Apu));
		this.studentManager.clearStudents();
		this.studentManager.addStudent(Jil);
		this.studentManager.addStudent(Al);
		assertTrue(this.studentManager.getAll().get(1).equals(Jil));
		assertTrue(this.studentManager.getAll().get(0).equals(Al));

		
	}

	@Test
	public void testClear() {
		this.studentManager.clearStudents();
		assertTrue(this.studentManager.getAll().size() ==0);
		this.studentManager.addStudent(Ned);
		this.studentManager.addStudent(Jil);
		this.studentManager.clearStudents();
		assertTrue(this.studentManager.getAll().size() ==0);

	}

	@Test
	public void testContains() {
		assertTrue(this.studentManager.getAll().contains(Xi));
		assertFalse(this.studentManager.getAll().contains(Yu));
		assertTrue(this.studentManager.deleteStudent(Xi));
		assertFalse(this.studentManager.getAll().contains(Xi));
		assertFalse(this.studentManager.deleteStudent(Xi));

	}

	@Test
	public void testIsEmpty() {
		assertFalse(this.studentManager.getAll().isEmpty());
		this.studentManager.clearStudents();
		assertTrue(this.studentManager.getAll().isEmpty());
	}

	@Test
	public void testIteratorInt() {
		Iterator<Student> iter = this.studentManager.getAll().iterator(0);
		assertTrue(iter.hasNext());
		assertTrue(iter.next().equals(Apu));

		iter = this.studentManager.getAll().iterator(1);
		assertTrue(iter.hasNext());
		assertTrue(iter.next().equals(Bob));
		
		this.studentManager.addStudent(Abu);
		iter = this.studentManager.getAll().iterator(0);
		assertTrue(iter.hasNext());
		assertTrue(iter.next().equals(Abu));
		assertTrue(iter.hasNext());
		assertTrue(iter.next().equals(Apu));

	}

	@Test
	public void testFirstIndex() {
		this.studentManager.addStudent(Bob);
		assertTrue(this.studentManager.getAll().firstIndex(Bob) == 1);
		assertTrue(this.studentManager.getAll().firstIndex(Jil) < 0);
		this.studentManager.addStudent(Abu);
		assertTrue(this.studentManager.getAll().firstIndex(Bob) == 2);

	}

	@Test
	public void testLastIndex() {
		this.studentManager.addStudent(Bob);
		this.studentManager.addStudent(Bob);
		this.studentManager.addStudent(Bob);
		assertTrue(this.studentManager.getAll().lastIndex(Bob) == 4);
		assertTrue(this.studentManager.getAll().lastIndex(Jil) < 0);
	}


}
