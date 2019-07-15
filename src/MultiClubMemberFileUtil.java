import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MultiClubMemberFileUtil {
private static FileLinesHelper linesHelper = new FileLinesHelper("multiClubMembers.txt");
	
	// Modify this method as necessary to convert a line of text from the file to a new item instance
	private static MultiClubMembers convertLineToItem(String line) {
		String[] parts = line.split("\t");
		MultiClubMembers mcm = new MultiClubMembers();
		mcm.setName(parts[0]);
		mcm.setId((Integer.parseInt(parts[1])));
		mcm.setMembershipPoints(Integer.parseInt(parts[2]));
		return mcm;
	}
	
	private static String convertItemToLineMCM(MultiClubMembers mcm) {
		return String.format("%s\t%d\t%d", mcm.getName(), mcm.getId(), mcm.getMembershipPoints());
	}

	public static List<MultiClubMembers> readFile() {
		List<String> lines = linesHelper.readFile();
		List<MultiClubMembers> items = new ArrayList<>(lines.size());//pre=allocating enough memory by setting the max to the same as lines list
		for (String line : lines) {
			items.add(convertLineToItem(line));
		}
		return items;
//		Or with streams...
//		return linesHelper.readFile().stream().map(PlayerFileUtil::convertLineToItem).collect(Collectors.toList());
	}
	
	public static void rewriteFile(List<MultiClubMembers> items) throws IOException {
		List<String> lines = new ArrayList<>(items.size());
		for (MultiClubMembers item : items) {
			lines.add(convertItemToLineMCM(item));
		}
		linesHelper.rewriteFile(lines);
//		Or with streams...
//		linesHelper.rewriteFile(items.stream().map(PlayerFileUtil::convertItemToLine).collect(Collectors.toList()));
	}
	
	public static void appendToFile(MultiClubMembers item) throws IOException {
		String line = convertItemToLineMCM(item);
		linesHelper.appendToFile(line);
	}
}

