package Map;

import java.util.ArrayList;

public class MapGraphics {
	
	private ArrayList<StringBuilder>lines;
	
	public MapGraphics(){
		lines = new ArrayList<StringBuilder>();
	}
	
	public ArrayList<StringBuilder>getLines() {
		return lines;
	}
	
	public String toString() {
		StringBuilder totalString = new StringBuilder();
		for(StringBuilder line : lines) {
			totalString.append(line + "\n");
		}
		return totalString.toString();
	}

	//Adds a line to the bottom of the text
	public void appendLine(String line) {
		appendLine(new StringBuilder(line));
	}
	
	//Adds a line to the bottom of the text
	public void appendLine(StringBuilder line) {
		addLineAt(lines.size(), line);
	}

	//Adds a line to the top of the text
	public void prependLine(String line) {
		prependLine(new StringBuilder(line));
	}
	
	//Adds a line to the top of the text
	public void prependLine(StringBuilder line) {
		addLineAt(0, line);
	}

	//Adds a line at the given location
	public void addLineAt(int pos, String line) {
		addLineAt(pos, new StringBuilder(line));
	}
	
	//Adds a line at the given location
	public void addLineAt(int pos, StringBuilder line) {
		lines.add(pos, line);
	}

	//Adds multiple lines to the bottom of the text
	public void addLines(ArrayList<StringBuilder>additionalLines) {
		lines.addAll(additionalLines);
	}
	
	//Returns the height of the text (which is equal to the number of independent lines)
	public int height() {
		return lines.size();
	}

	//Formatting helper: repeatedly adds a String to a StringBuilder until the required length is met
	private void padStringBuilder(StringBuilder sb, String str, int targetLength) {
		if(str.length() == 0) {
			return;
		}
		while(sb.length() < targetLength) {
			sb.append(str);
		}
	}

	//Formatting helper: finds the length of the longest line
	private int maxLength() {
		int max = 0;
		for(StringBuilder line : lines) {
			if(line.length() > max) {
				max = line.length();
			}
		}
		return max;
	}

	//Formatting helper: adds lines to the bottom of an ArrayList until the required length is met
	private void padArrayList(ArrayList<StringBuilder>sbList, String str, int targetHeight) {
		while(sbList.size() < targetHeight) {
			sbList.add(new StringBuilder(str));
		}
	}
	
	//Adds spaces to the end of each line to make them all as long as the longest line
	public void padToEdge() {
		padToEdge(0);
	}

	//Adds spaces to make all the lines extraSpaces longer than what the longest originally was
	public void padToEdge(int extraSpaces) {
		int max = maxLength();
		for(StringBuilder line : lines) {
			padStringBuilder(line, " ", max+extraSpaces);
		}
	}
	
	//Adds empty lines until the text reaches the target height
	public void padToHeight(int h) {
		padArrayList(lines, "", h);
	}
	
	//Adds a particular string to the left side of each line
	public void addLeftWall(String wallPattern) {
		for(StringBuilder line : lines) {
			line.insert(0, wallPattern);
		}
	}

	//Establishes a right edge, then adds a particular string to the right side of each line
	public void addRightWall(String wallPattern) {
		padToEdge();
		for(StringBuilder line : lines) {
			line.append(wallPattern);
		}
	}
	
	//Stretches the given pattern along the bottom of the text
	public void addFloor(String floorPattern) {
		addHorizontalDivider(lines.size(), floorPattern);
	}

	//Stretches the given pattern along the top of the text
	public void addCeiling(String ceilingPattern) {
		addHorizontalDivider(0, ceilingPattern);
	}
	
	//Creates a new row consisting of the given pattern
	public void addHorizontalDivider(int pos, String pattern) {
		if(pattern.equals("")) {
			return;
		}
		StringBuilder sb = new StringBuilder(pattern);
		padStringBuilder(sb, pattern, maxLength());
		lines.add(pos, sb);
	}
	
	//Text-merging helper: makes fresh copies of the lines from another panel
	private ArrayList<StringBuilder>copyPanelLines(MapGraphics panel) {
		ArrayList<StringBuilder>copiedLines = new ArrayList<StringBuilder>();
		for(StringBuilder line : panel.getLines()) {
			copiedLines.add(new StringBuilder(line));
		}
		return copiedLines;
	}
	
	//Adds another panel's text to the right of the current text, with a divider in between (and a gap on either side of the divider)
	public void appendPanelRight(MapGraphics panel, int gap, String divider, int gap2) {
		ArrayList<StringBuilder>lines2 = copyPanelLines(panel);
		padArrayList(lines, "", lines2.size());
		padArrayList(lines2, "", lines.size());
		padToEdge(gap);
		addRightWall(divider);
		padToEdge(gap2);
		for(int lineNum = 0; lineNum < lines.size(); lineNum++) {
			lines.get(lineNum).append(lines2.get(lineNum));
		}
	}

	//Adds another panel's text below the current text, with a divider in between (and a gap on either side of the divider)
	public void appendPanelBelow(MapGraphics panel, int gap, String divider, int gap2) {
		ArrayList<StringBuilder>lines2 = copyPanelLines(panel);
		for(int i = 0; i < gap; i++) {
			lines.add(new StringBuilder());
		}
		int pos = lines.size();
		for(int i = 0; i < gap2; i++) {
			lines.add(new StringBuilder());
		}
		lines.addAll(lines2);
		addHorizontalDivider(pos, divider);
	}
	
	//Creates a box around the panel
	public void makeBox(String fullPattern) {
		makeBox(fullPattern, fullPattern);
	}
	
	//Creates a box around the panel
	public void makeBox(String wallPattern, String ceilingAndFloorPattern) {
		addFloor(ceilingAndFloorPattern);
		addCeiling(ceilingAndFloorPattern);
		addLeftWall(wallPattern);
		addRightWall(wallPattern);
	}
	
	//Changes the corners of the panel
	public void specifyCorners(char corner) {
		specifyCorners(corner, corner, corner, corner);
	}
	
	//Changes the corners of the panel
	public void specifyCorners(char topLeftCorner, char topRightCorner, char bottomLeftCorner, char bottomRightCorner) {
		StringBuilder firstLine = lines.get(0);
		StringBuilder lastLine = lines.get(lines.size()-1);
		firstLine.setCharAt(0, topLeftCorner);
		firstLine.setCharAt(firstLine.length()-1, topRightCorner);
		lastLine.setCharAt(0, bottomLeftCorner);
		lastLine.setCharAt(lastLine.length()-1, bottomRightCorner);
	}
	
	//Overlaying helper: Inserts a String on top of a StringBuilder starting at the given location
	private void replaceInStringBuilder(StringBuilder base, String str, int startPos) {
		int sbLength = base.length();
		base.insert(startPos, str);
		while(base.length() > sbLength) {
			int deletePos = (startPos+str.length() < base.length()) ? startPos + str.length() : base.length()-1;
			base.deleteCharAt(deletePos);
		}
	}

	//Lays text on top of the panel
	public void overlay(String line, int rowNum, int colNum) {
		replaceInStringBuilder(lines.get(rowNum), line, colNum);
	}
	
	//Lays text on top of the panel
	public void overlay(ArrayList<String> text, int rowNum, int colNum) { 
		int counter = 0;
		for(String line : text) {
			if(counter + rowNum >= lines.size()) {
				return;
			}
			overlay(line, counter + rowNum, colNum);
			counter++;
		}
	}
	
	//Adds a number to the start of each line
	public void numberLines(String numberSeparator, int startNum, int startLine, int lineCount) {
		for(int i = 0; i < lineCount; i++) {
			StringBuilder sb = lines.get(i+startLine);
			sb.insert(0, (i+startNum) + numberSeparator);
		}
	}
	
	//Adds a number to the start of each line
	public void numberLines(String numberSeparator) {
		numberLines(numberSeparator, 1, 0, lines.size());
	}
}