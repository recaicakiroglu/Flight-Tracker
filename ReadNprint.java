import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReadNprint {
	public String ReadFile(String STR) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(STR), "UTF-8"));
		String line;
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
			stringBuffer.append("\n");
		}
		String text = stringBuffer.toString();
		return text;
	}

	public void Printer(ArrayList<Route> temp, PrintWriter writer, Calendar limitDate) {
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
			Calendar c1 = (Calendar) route.get(0).getDate().clone();

			if (c1.before(limitDate)) {
				temp.get(i).setStatus(1);
			}
		}

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 1) {
				continue;
			}
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				if (j == temp.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}
	}

	public void PrintExcluding(ArrayList<Route> temp, PrintWriter writer, String ID) {
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				String PortID = route.get(j).getName().substring(0, 2);
				if (PortID.equals(ID)) {
					temp.get(i).setStatus(1);
				}
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 1) {
				continue;
			}
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				if (j == temp.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}

	}

	public void PrintFrom(ArrayList<Route> temp, PrintWriter writer, String ID) {
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				String PortID = route.get(j).getName().substring(0, 2);
				if (!PortID.equals(ID)) {
					temp.get(i).setStatus(1);
				}
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 1) {
				continue;
			}
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				if (j == temp.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}

	}

	public void PrintCheaper(ArrayList<Route> temp, PrintWriter writer, String ID) {
		int limit = Integer.parseInt(ID);
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getMoney() < limit) {
				temp.get(i).setStatus(1);
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 0) {
				continue;
			}
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				if (j == temp.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}

	}

	public void PrintProper(ArrayList<Route> temp, PrintWriter writer, Calendar limitDate) {
		Comparator<Route> TimeCom = new TimeComparator();
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
			Calendar c1 = (Calendar) route.get(0).getDate().clone();
			if (c1.before(limitDate)) {
				temp.get(i).setStatus(1);
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < temp.size(); j++) {
				if (i != j) {
					if ((temp.get(i).getMoney() > temp.get(j).getMoney()
							&& ((TimeCom.compare(temp.get(i), temp.get(j)) == 1
									|| TimeCom.compare(temp.get(i), temp.get(j)) == 0)))) {
						temp.get(i).setStatus(1);
					}
				}

			}
		}

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 1) {
				continue;
			}
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				if (j == temp.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}

	}

	public ArrayList<Route> Proper(ArrayList<Route> temp, PrintWriter writer, Calendar limitDate) {
		Comparator<Route> TimeCom = new TimeComparator();
		ArrayList<Route> Final = new ArrayList<Route>();
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
			Calendar c1 = (Calendar) route.get(0).getDate().clone();
			if (c1.before(limitDate)) {
				temp.get(i).setStatus(1);
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < temp.size(); j++) {
				if (i != j) {
					if ((temp.get(i).getMoney() > temp.get(j).getMoney()
							&& ((TimeCom.compare(temp.get(i), temp.get(j)) == 1
									|| TimeCom.compare(temp.get(i), temp.get(j)) == 0)))) {
						temp.get(i).setStatus(1);
					}
				}

			}
		}

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 1) {
				continue;
			}
			Final.add(temp.get(i));

		}
		return Final;
	}

	@SuppressWarnings("static-access")
	public void PrintQuicker(ArrayList<Route> temp, PrintWriter writer, String ID) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm EEE", Locale.US);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(ID));
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
			Calendar c1 = (Calendar) route.get(route.size() - 1).getDate().clone();
			c1.add(c1.HOUR, route.get(route.size() - 1).getTime().getHour());
			c1.add(c1.MINUTE, route.get(route.size() - 1).getTime().getMinute());
			if (c1.before(cal)) {
				temp.get(i).setStatus(1);
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getStatus() == 0) {
				continue;
			}
			for (int j = 0; j < temp.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
				if (j == temp.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + temp.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}

	}

	public void PrintCheapest(ArrayList<Route> temp, PrintWriter writer, Calendar limitDate) {
		Comparator<Route> moneyCom = new MoneyComparator();
		int cut = 0;
		Collections.sort(temp, moneyCom);
		ArrayList<Route> holder = new ArrayList<Route>();
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
			Calendar c1 = (Calendar) route.get(0).getDate().clone();
			if (limitDate.before(c1)) {
				holder.add(temp.get(i));
			}
		}

		for (int i = 0; i < holder.size(); i++) {
			if (holder.get(i).getMoney() != holder.get(i + 1).getMoney()) {
				cut = i + 1;
				break;
			}
		}

		for (int i = 0; i < cut; i++) {

			for (int j = 0; j < holder.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = holder.get(i).getRoutes();
				if (j == holder.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(
							route.get(j).from().getName() + "->" + holder.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + holder.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}

	}

	public void PrintQuickest(ArrayList<Route> temp, PrintWriter writer, Calendar limitDate) {
		Comparator<Route> TimeCom = new TimeComparator();
		Collections.sort(temp, TimeCom);
		int cut = 0;
		ArrayList<Route> holder = new ArrayList<Route>();
		for (int i = 0; i < temp.size(); i++) {
			ArrayList<DirectedEdge> route = temp.get(i).getRoutes();
			Calendar c1 = (Calendar) route.get(0).getDate().clone();

			if (limitDate.before(c1)) {
				holder.add(temp.get(i));
			}
		}

		for (int i = 0; i < holder.size(); i++) {
			if ((holder.get(i).getHour() + holder.get(i).getMin()) != holder.get(i + 1).getHour()
					+ holder.get(i + 1).getMin()) {
				cut = i + 1;
				break;
			}
		}
		for (int i = 0; i < cut; i++) {
			for (int j = 0; j < holder.get(i).getRoutes().size(); j++) {
				ArrayList<DirectedEdge> route = holder.get(i).getRoutes();
				if (j == holder.get(i).getRoutes().size() - 1) {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(
							route.get(j).from().getName() + "->" + holder.get(i).getRoutes().get(j).to().getName());
				} else {
					writer.printf(route.get(j).getName() + "\t");
					writer.printf(route.get(j).from().getName() + "->" + holder.get(i).getRoutes().get(j).to().getName()
							+ "||");
				}
			}
			writer.printf("\t%02d:%02d", temp.get(i).getHour(), temp.get(i).getMin());
			writer.printf("/" + temp.get(i).getMoney());
			writer.printf("\r\n");
		}
	}

	@SuppressWarnings("static-access")
	public void Control(String AllPath, ArrayList<DirectedEdge> edges, ArrayList<Route> Routes) {
		boolean timeControl = false;
		int money = 0;

		String[] RouteID = AllPath.split("-");
		if (RouteID[0].equals(RouteID[1])) {
			ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();

			for (DirectedEdge e : edges) {
				if (e.getName().equals(RouteID[0])) {
					path.add(e);

				}
			}
			Calendar first = (Calendar) path.get(0).getDate().clone();
			Route tempRoute = new Route(path.get(0).getCash(), path.get(0).getTime().getHour(),
					path.get(0).getTime().getMinute(), path, first, 0);
			Routes.add(tempRoute);
		}
		ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
		for (int i = 0; i < RouteID.length; i++) {
			for (DirectedEdge e : edges) {
				if (e.getName().equals(RouteID[i])) {
					path.add(e);
					money = money + e.getCash();
				}
			}
		}
		for (int i = 0; i < path.size() - 1; i++) {
			Calendar c1 = (Calendar) path.get(i).getDate().clone();
			Calendar c2 = (Calendar) path.get(i + 1).getDate().clone();
			path.get(i).getDate();
			c1.add(c1.HOUR, path.get(i).getTime().getHour());
			c1.add(c1.MINUTE, path.get(i).getTime().getMinute());
			if (c1.after(c2)) {
				timeControl = true;
			}
		}

		if (timeControl == false) {
			Calendar first = (Calendar) path.get(0).getDate().clone();
			Calendar last = (Calendar) path.get(path.size() - 1).getDate().clone();
			last.add(last.HOUR, path.get(path.size() - 1).getTime().getHour());
			last.add(last.MINUTE, path.get(path.size() - 1).getTime().getMinute());
			long diff = last.getTimeInMillis() - first.getTimeInMillis();
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000);

			Route tempRoute = new Route(money, diffHours, diffMinutes, path, first, 0);
			Routes.add(tempRoute);
		}
		money = 0;
		timeControl = false;

	}

	public class MoneyComparator implements Comparator<Route> {

		@Override
		public int compare(Route o1, Route o2) {
			return o1.getMoney() - o2.getMoney();

		}

	}

	public class TimeComparator implements Comparator<Route> {

		@Override
		public int compare(Route o1, Route o2) {
			if (o1.getHour() > o2.getHour()) {
				return 1;
			}
			if (o1.getHour() < o2.getHour()) {
				return -1;
			}
			if (o1.getHour() == o2.getHour()) {
				if (o1.getMin() > o2.getMin()) {
					return 1;
				}
				if (o1.getMin() == o2.getMin()) {
					return 0;
				}
				if (o1.getMin() < o2.getMin()) {
					return -1;
				}
			}
			return 0;

		}
	}

	public void path(ArrayList<Integer> list, ArrayList<DirectedEdge> edges, ArrayList<String> AllPaths) {
		Map<Integer, ArrayList<DirectedEdge>> map = new HashMap<Integer, ArrayList<DirectedEdge>>();
		int counter = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
			for (DirectedEdge e : edges) {
				if (e.from().getNo() == list.get(i) && e.to().getNo() == list.get(i + 1)) {
					path.add(e);
				}
			}
			map.put(counter, path);
			counter++;
		}
		if (list.size() == 2) {
			for (int i = 0; i < map.get(0).size(); i++) {
				String STR = map.get(0).get(i).getName() + "-" + map.get(0).get(i).getName();
				AllPaths.add(STR);
			}
		}
		if (list.size() == 3) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName();
					AllPaths.add(STR);
				}
			}
		}
		if (list.size() == 4) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName() + "-"
								+ map.get(2).get(j2).getName();
						AllPaths.add(STR);
					}
				}
			}
		}
		if (list.size() == 5) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName() + "-"
									+ map.get(2).get(j2).getName() + "-" + map.get(3).get(k).getName();
							AllPaths.add(STR);
						}
					}
				}
			}
		}
		if (list.size() == 6) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName() + "-"
										+ map.get(2).get(j2).getName() + "-" + map.get(3).get(k).getName() + "-"
										+ map.get(4).get(k2).getName();
								AllPaths.add(STR);
							}
						}
					}
				}
			}
		}

		if (list.size() == 7) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								for (int l = 0; l < map.get(5).size(); l++) {
									String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName() + "-"
											+ map.get(2).get(j2).getName() + "-" + map.get(3).get(k).getName() + "-"
											+ map.get(4).get(k2).getName() + "-" + map.get(5).get(l).getName();
									AllPaths.add(STR);
								}
							}
						}
					}
				}
			}
		}
		if (list.size() == 8) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								for (int l = 0; l < map.get(5).size(); l++) {
									for (int l2 = 0; l2 < map.get(6).size(); l2++) {
										String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName()
												+ "-" + map.get(2).get(j2).getName() + "-" + map.get(3).get(k).getName()
												+ "-" + map.get(4).get(k2).getName() + "-" + map.get(5).get(l).getName()
												+ "-" + map.get(6).get(l2).getName();
										AllPaths.add(STR);
									}
								}
							}
						}
					}
				}
			}
		}
		if (list.size() == 9) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								for (int l = 0; l < map.get(5).size(); l++) {
									for (int l2 = 0; l2 < map.get(6).size(); l2++) {
										for (int m = 0; m < map.get(7).size(); m++) {
											String STR = map.get(0).get(i).getName() + "-" + map.get(1).get(j).getName()
													+ "-" + map.get(2).get(j2).getName() + "-"
													+ map.get(3).get(k).getName() + "-" + map.get(4).get(k2).getName()
													+ "-" + map.get(5).get(l).getName() + "-"
													+ map.get(6).get(l2).getName() + "-" + map.get(7).get(m).getName();
											AllPaths.add(STR);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (list.size() == 10) {
			for (int i = 0; i < map.get(0).size(); i++) {

				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								for (int l = 0; l < map.get(5).size(); l++) {
									for (int l2 = 0; l2 < map.get(6).size(); l2++) {
										for (int m = 0; m < map.get(7).size(); m++) {
											for (int m2 = 0; m2 < map.get(8).size(); m2++) {
												String STR = map.get(0).get(i).getName() + "-"
														+ map.get(1).get(j).getName() + "-"
														+ map.get(2).get(j2).getName() + "-"
														+ map.get(3).get(k).getName() + "-"
														+ map.get(4).get(k2).getName() + "-"
														+ map.get(5).get(l).getName() + "-"
														+ map.get(6).get(l2).getName() + "-"
														+ map.get(7).get(m).getName() + "-"
														+ map.get(8).get(m2).getName();
												AllPaths.add(STR);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (list.size() == 11) {
			for (int i = 0; i < map.get(0).size(); i++) {

				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								for (int l = 0; l < map.get(5).size(); l++) {
									for (int l2 = 0; l2 < map.get(6).size(); l2++) {
										for (int m = 0; m < map.get(7).size(); m++) {
											for (int m2 = 0; m2 < map.get(8).size(); m2++) {
												for (int n = 0; n < map.get(9).size(); n++) {
													String STR = map.get(0).get(i).getName() + "-"
															+ map.get(1).get(j).getName() + "-"
															+ map.get(2).get(j2).getName() + "-"
															+ map.get(3).get(k).getName() + "-"
															+ map.get(4).get(k2).getName() + "-"
															+ map.get(5).get(l).getName() + "-"
															+ map.get(6).get(l2).getName() + "-"
															+ map.get(7).get(m).getName() + "-"
															+ map.get(8).get(m2).getName() + "-"
															+ map.get(9).get(n).getName();
													AllPaths.add(STR);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (list.size() == 12) {
			for (int i = 0; i < map.get(0).size(); i++) {
				for (int j = 0; j < map.get(1).size(); j++) {
					for (int j2 = 0; j2 < map.get(2).size(); j2++) {
						for (int k = 0; k < map.get(3).size(); k++) {
							for (int k2 = 0; k2 < map.get(4).size(); k2++) {
								for (int l = 0; l < map.get(5).size(); l++) {
									for (int l2 = 0; l2 < map.get(6).size(); l2++) {
										for (int m = 0; m < map.get(7).size(); m++) {
											for (int m2 = 0; m2 < map.get(8).size(); m2++) {
												for (int n = 0; n < map.get(9).size(); n++) {
													for (int n2 = 0; n2 < map.get(10).size(); n2++) {
														String STR = map.get(0).get(i).getName() + "-"
																+ map.get(1).get(j).getName() + "-"
																+ map.get(2).get(j2).getName() + "-"
																+ map.get(3).get(k).getName() + "-"
																+ map.get(4).get(k2).getName() + "-"
																+ map.get(5).get(l).getName() + "-"
																+ map.get(6).get(l2).getName() + "-"
																+ map.get(7).get(m).getName() + "-"
																+ map.get(8).get(m2).getName() + "-"
																+ map.get(9).get(n).getName() + "-"
																+ map.get(9).get(n2).getName();
														AllPaths.add(STR);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
