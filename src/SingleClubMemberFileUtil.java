import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleClubMemberFileUtil {
private static FileLinesHelper linesHelper = new FileLinesHelper("singleClubMembers.txt");
	
	// Modify this method as necessary to convert a line of text from the file to a new item instance
	private static SingleClubMember convertLineToItem(String line) {
		String[] parts = line.split("\t");
		SingleClubMember member = new SingleClubMember();
		member.setName(parts[0]);
		member.setId(Integer.parseInt(parts[1]));
		member.setClub(parts[2]);
		return member;
	}
	
	private static String convertItemToLineMembers(SingleClubMember member) {
		return String.format("%s\t%d\t%s", member.getName(), member.getId(), member.getClub());
	}

	public static List<SingleClubMember> readFile() {
		List<String> lines = linesHelper.readFile();
		List<SingleClubMember> items = new ArrayList<>(lines.size());//pre=allocating enough memory by setting the max to the same as lines list
		for (String line : lines) {
			items.add(convertLineToItem(line));
		}
		return items;
//		Or with streams...
//		return linesHelper.readFile().stream().map(PlayerFileUtil::convertLineToItem).collect(Collectors.toList());
	}
	
	public static void rewriteFile(List<SingleClubMember> items) throws IOException {
		List<String> lines = new ArrayList<>(items.size());
		for (SingleClubMember item : items) {
			lines.add(convertItemToLineMembers(item));
		}
		linesHelper.rewriteFile(lines);
//		Or with streams...
//		linesHelper.rewriteFile(items.stream().map(PlayerFileUtil::convertItemToLine).collect(Collectors.toList()));
	}
	
	public static void appendToFile(SingleClubMember item) throws IOException {
		String line = convertItemToLineMembers(item);
		linesHelper.appendToFile(line);
	}
}

