import java.time.LocalTime;
import java.util.Calendar;

public class DirectedEdge {
	private Vertex v, w;
	private String name;
	private Calendar date;
	private int cash;
	private LocalTime time;

	public DirectedEdge(Vertex v, Vertex w, String name, Calendar date, int cash, LocalTime time) {
		super();
		this.v = v;
		this.w = w;
		this.name = name;
		this.date = date;
		this.cash = cash;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Vertex from() {
		return v;
	}

	public Vertex to() {
		return w;
	}

	public int contains(DirectedEdge str) {
		if (this.from().equals(str.from())) {
			return 1;
		} else {
			return 0;
		}

	}

}