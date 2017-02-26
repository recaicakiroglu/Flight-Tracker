
import java.util.ArrayList;
import java.util.Calendar;


public class Route{
private int Money;
private long hour;
private long min;
private ArrayList<DirectedEdge> Routes;
private Calendar first;
private int status;
public Route(int money, long hour, long min, ArrayList<DirectedEdge> routes, Calendar first, int status) {
	super();
	Money = money;
	this.hour = hour;
	this.min = min;
	Routes = routes;
	this.first = first;
	this.status = status;
}
public int getMoney() {
	return Money;
}
public void setMoney(int money) {
	Money = money;
}
public long getHour() {
	return hour;
}
public void setHour(long hour) {
	this.hour = hour;
}
public long getMin() {
	return min;
}
public void setMin(long min) {
	this.min = min;
}
public ArrayList<DirectedEdge> getRoutes() {
	return Routes;
}
public void setRoutes(ArrayList<DirectedEdge> routes) {
	Routes = routes;
}
public Calendar getFirst() {
	return first;
}
public void setFirst(Calendar first) {
	this.first = first;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}








}
