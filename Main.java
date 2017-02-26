import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

public class Main {
	/**
	 * main controls everything in this program merge,DFS,Generate All Paths and
	 * Finally Print
	 * 
	 * @param args
	 *            airports flights commands and output.txt
	 * @throws IOException
	 *             for missing input or output
	 * @throws ParseException
	 *             if there is a parse error throw this exception
	 */
	public static void main(String[] args) throws IOException, ParseException {
		Merge mergeObj = new Merge();
		int adjsize = mergeObj.mergeAirport(args[0]);
		ReadNprint readObj = new ReadNprint();
		File fileObj = new File(args[3]);
		FileWriter fileWriterObj = new FileWriter(fileObj.getAbsoluteFile());
		PrintWriter writer = new PrintWriter(fileWriterObj);
		String CommandSTR = readObj.ReadFile("commands.txt");
		String[] Commands = CommandSTR.split("\n");
		GeneratorNControl generateObj = new GeneratorNControl();
		for (int i = 0; i < Commands.length; i++) {
			generateObj.CommandController(Commands[i], writer, adjsize, args[0], args[1]);
		}
		writer.close();
	}
}
