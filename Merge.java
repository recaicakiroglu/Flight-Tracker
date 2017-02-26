import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Merge {
	/**
	 * This section for count portsize for estimate graph size
	 * 
	 * @return size of graph
	 * @throws IOException
	 *             exception for input output
	 * @throws ParseException
	 *             if there is a parse error throw this exception
	 */
	public int mergeAirport(String STR) throws IOException, ParseException {
		ReadNprint readObj = new ReadNprint();
		int portsize = 0;
		String Airporttext = readObj.ReadFile(STR);
		String[] AirportLines = Airporttext.split("\n");

		for (int i = 0; i < AirportLines.length; i++) {
			String[] portstemp = AirportLines[i].split("\t");

			String city = portstemp[0];
			int size = city.length();
			String PortsTemp = AirportLines[i].substring(size + 1, AirportLines[i].length());
			String[] ports = PortsTemp.split("\t");

			for (int j = 0; j < ports.length; j++) {
				portsize++;
			}
		}
		return portsize;
	}

	/**
	 * this section for adding flight routes
	 * 
	 * @param graph
	 *            graph for source to destination
	 * @param edgearray
	 *            temp array
	 * @throws IOException
	 *             exception for input output
	 * @throws ParseException
	 *             if there is a parse error throw this exception
	 */
	public void mergeFlight(EdgeWeightedDigraph graph, ArrayList<DirectedEdge> edgearray, String PORT, String Flight)
			throws IOException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm EEE", Locale.US);
		ReadNprint readObj = new ReadNprint();
		int iterator = 0;
		String Airporttext = readObj.ReadFile(PORT);
		String[] AirportLines = Airporttext.split("\n");

		for (int i = 0; i < AirportLines.length; i++) {
			String[] portstemp = AirportLines[i].split("\t");

			String city = portstemp[0];
			int size = city.length();
			String PortsTemp = AirportLines[i].substring(size + 1, AirportLines[i].length());
			String[] ports = PortsTemp.split("\t");

			for (int j = 0; j < ports.length; j++) {
				graph.adj[iterator].setName(ports[j]);
				graph.adj[iterator].setCity(city);
				iterator++;
			}
		}
		String flighttext = readObj.ReadFile(Flight);
		String[] flightLines = flighttext.split("\n");
		int temp = 0;
		int temp2 = 0;
		for (int i = 0; i < flightLines.length; i++) {
			String[] flightstemp = flightLines[i].split("\t");
			String[] edges = flightstemp[1].split("->");
			for (int k = 0; k < graph.adj.length; k++) {
				if (graph.adj[k].getName().equals(edges[0]))
					temp = k;
			}
			for (int k = 0; k < graph.adj.length; k++) {
				if (graph.adj[k].getName().equals(edges[1]))
					temp2 = k;
			}

			Vertex v = new Vertex(edges[0], temp);
			Vertex w = new Vertex(edges[1], temp2);

			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(flightstemp[2]));
			LocalTime elapsedtime = LocalTime.parse(flightstemp[3]);
			int cash = Integer.parseInt(flightstemp[4]);
			DirectedEdge edge = new DirectedEdge(v, w, flightstemp[0], cal, cash, elapsedtime);
			graph.addEdge(edge);
			edgearray.add(edge);
		}

	}
}
