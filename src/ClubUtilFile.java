
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClubUtilFile {

	private static FileLinesHelper linesHelper = new FileLinesHelper("clubs.txt");

	// Modify this method as necessary to convert a line of text from the file to a
	// new item instance
	private static Club convertLineToItem(String line) {
		String[] parts = line.split("\t");
		Club club = new Club();
		club.setName(parts[0]);
		club.setAdress(parts[1]);

		return club;
	}

	private static String convertItemToLineClub(Club club) {
		return String.format("%s\t%s", club.getName(), club.getAdress());
	}

	public static List<Club> readFile() {
		List<String> lines = linesHelper.readFile();
		List<Club> items = new ArrayList<>(lines.size());// pre=allocating enough memory by setting the max to the same
															// as lines list
		for (String line : lines) {
			items.add(convertLineToItem(line));
		}
		return items;
//			Or with streams...
//			return linesHelper.readFile().stream().map(PlayerFileUtil::convertLineToItem).collect(Collectors.toList());
	}

	public static void rewriteFile(List<Club> items) throws IOException {
		List<String> lines = new ArrayList<>(items.size());
		for (Club item : items) {
			lines.add(convertItemToLineClub(item));
		}
		linesHelper.rewriteFile(lines);
//			Or with streams...
//			linesHelper.rewriteFile(items.stream().map(PlayerFileUtil::convertItemToLine).collect(Collectors.toList()));
	}

	public static void appendToFile(Club item) throws IOException {
		String line = convertItemToLineClub(item);
		linesHelper.appendToFile(line);
	}
}
