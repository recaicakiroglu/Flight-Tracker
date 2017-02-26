import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class GeneratorNControl {
	/**
	 * This section for read the command file and control printing situation
	 * 
	 * @param command
	 *            command.txt
	 * @param writer
	 *            writer for output
	 * @param adjsize
	 *            graph size
	 * @throws ParseException
	 *             if there is a parse error throw this exception
	 * @throws IOException
	 *             for missing input or output
	 */
	public void CommandController(String command, PrintWriter writer, int adjsize, String Port, String Flight)
			throws ParseException, IOException {
		
		ReadNprint readObj = new ReadNprint();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		String[] arg = command.split("\t");
		Calendar LimitDate = Calendar.getInstance();
		LimitDate.setTime(date.parse(arg[2]));
		String[] srcdes = arg[1].split("->");
		// Reading command line by line
		if (arg[0].equals("listall")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> tempone = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			readObj.Printer(tempone, writer, LimitDate);
			writer.println();
			writer.println();

		}
		if (arg[0].equals("listcheapest")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> temptwo = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			readObj.PrintCheapest(temptwo, writer, LimitDate);
			writer.println();
			writer.println();
		}
		if (arg[0].equals("listquickest")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> tempthree = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			readObj.PrintQuickest(tempthree, writer, LimitDate);
			writer.println();
			writer.println();
		}
		if (arg[0].equals("listproper")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");

			ArrayList<Route> tempfour = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);

			readObj.PrintProper(tempfour, writer, LimitDate);
			writer.println();
			writer.println();
		}
		if (arg[0].equals("listexcluding")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> tempfive = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			ArrayList<Route> tempsix = readObj.Proper(tempfive, writer, LimitDate);
			readObj.PrintExcluding(tempsix, writer, arg[3]);

			writer.println();
			writer.println();
		}
		if (arg[0].equals("listonlyfrom")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> tempseven = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			ArrayList<Route> tempeight = readObj.Proper(tempseven, writer, LimitDate);
			readObj.PrintFrom(tempeight, writer, arg[3]);
			writer.println();
			writer.println();
		}
		if (arg[0].equals("listcheaper")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> tempnine = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			ArrayList<Route> tempten = readObj.Proper(tempnine, writer, LimitDate);
			readObj.PrintCheaper(tempten, writer, arg[3]);

			writer.println();
			writer.println();
		}
		if (arg[0].equals("listquicker")) {
			writer.printf("Command : ");
			writer.printf(command);
			writer.printf("\r\n");
			ArrayList<Route> tempeleven = AllPathGenerator(adjsize, srcdes[0], srcdes[1], Port, Flight);
			ArrayList<Route> templast = readObj.Proper(tempeleven, writer, LimitDate);

			readObj.PrintQuicker(templast, writer, arg[3]);

			writer.println();
			writer.println();
		}
	}

	/**
	 * this section generate all possible paths and stores in a arraylist
	 * 
	 * @param adjsize
	 *            size of graph
	 * @param src
	 *            source
	 * @param des
	 *            destination
	 * @return arraylist
	 * @throws IOException
	 *             for missing input or output
	 * @throws ParseException
	 *             if there is a parse error throw this exception
	 */
	public ArrayList<Route> AllPathGenerator(int adjsize, String src, String des, String Port, String Flight)
			throws IOException, ParseException {
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(adjsize);
		Merge mergeObj = new Merge();
		ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
		ArrayList<Route> Routes = new ArrayList<Route>();
		ReadNprint readObj = new ReadNprint();
		mergeObj.mergeFlight(graph, edges, Port, Flight);

		for (int i = 0; i < graph.adj.length; i++) {
			for (int j = 0; j < graph.adj.length; j++) {
				if (graph.adj[i].getCity().equals(src) && graph.adj[j].getCity().equals(des)) {
					new DFS(graph, i, j);
				}
			}
		}
		ArrayList<String> AllPath = new ArrayList<String>();
		for (int i = 0; i < DFS.hashmap.size(); i++) {
			readObj.path(DFS.hashmap.get(i), edges, AllPath);
			DFS.hashmap.get(i).clear();
		}
		for (int i = 0; i < AllPath.size(); i++) {
			readObj.Control(AllPath.get(i), edges, Routes);
		}
		AllPath.clear();
		return Routes;
	}
}
