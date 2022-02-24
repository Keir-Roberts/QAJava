package main;

public class cat {
private int id;
private String name;
private int age;
public int getId() {
	return id;
}
public void setID(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public cat(int id, String name, int age) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
}
}
