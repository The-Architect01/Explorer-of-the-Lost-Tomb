package Rooms;
//
public class MapChecker {//DISABLED
//
//	public MapChecker(char[][] map, int[] coorEn, int[] coorEx) {
//		this.map = map;
//		coorExit = coorEx;
//		coorEntr = coorEn;
//		currentCoor[0] = coorEn[0];
//		currentCoor[1] = coorEn[1];
//	}
//
//	char[][] map;
//	int[] coorExit = new int[2];
//	int[] coorEntr = new int[2];
//	int[] currentCoor = new int[2];
//
//	public boolean checkMap() {
//		if (map[coorEntr[0]][coorEntr[1]] == '·') {return false;}
//		if (map[coorExit[0]][coorExit[1]] == '·') {return false;}
//		if (verticalIsland()||horizontalIsland()) {return false;}
//		return true;
//	}
//
//	private boolean verticalIsland() {
//		for (int i=0; i<map.length-1;i++) {
//			for (int j = 0; j<map.length-1;j++) {
//				int count = 0;
//				if ((vertical(i,j) && vertical(i,j+1))) {
//					count++;
//				}
//				if (count > charsInCol(j)) {return true;}
//			}
//		}
//		return false;
//	}
//
//	private boolean horizontalIsland() {
//		for (int j=0; j<map.length-1;j++) {
//			for (int i = 0; i<map.length-1;i++) {
//				int count = 0;
//				if ((horizontal(i,j) && horizontal(i+1,j))) {
//					count++;
//				}
//				if (count > charsInRow(i)) {return true;}
//			}
//
//		}
//		return false;
//	}
//
//	private boolean vertical(int row, int col) {
//		if ((map[row][col] == Room.HORIZONTAL_WALL && (map[row][col+1] == Room.LEFT_DIV || map[row][col+1] == Room.RIGHT_DIV || map[row][col+1] ==Room.DOWN_DIV || map[row][col+1] == Room.UP_DIV))||(map[row][col] == Room.LEFT_DIV && map[row][col+1]==Room.RIGHT_DIV)) {
//			return true;
//		}
//		return false;
//	}
//	
//	private boolean horizontal(int row, int col) {
//		if ((map[row][col] == Room.VERTICAL_WALL/ && (map[row+1][col] == Room.LEFT_DIV || map[row+1][col] == Room.RIGHT_DIV  || map[row+1][col] == Room.UP_DIV))||(map[row][col] == Room.UP_DIV && map[row+1][col]==Room.DOWN_DIV)) {return true;}
//		return false;
//	}
//	
//	private int charsInRow(int row) {
//		int count=0;
//		for (int i = 0; i<map.length;i++) {
//			if (horizontal(row,i)) {count++;}
//		}
//		return count;
//	}
//	private int charsInCol(int col) {
//		int count=0;
//		for(int i =0; i<map.length;i++) {
//			if (vertical(i,col)) {count++;}
//		}
//		return count;
//	}
}
