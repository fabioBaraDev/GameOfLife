// https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

// It’s a simple 2-dimensional automata where each cell can be either alive or dead. 
// Each “clock tick” updates the live/dead status of each cell based on the following rules: 
// * Any live cell with fewer than two live neighbors dies, as if by underpopulation.
// * Any live cell with two or three live neighbors lives on to the next generation.
// * Any live cell with more than three live neighbors dies, as if by overpopulation.
// * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction. 
//
// Your code should demonstrate its correct with this test: 
// 1st generation: [ [0,1,0], [0,1,0], [0,1,0] ]
// 2nd generation: [ [0,0,0], [1,1,1], [0,0,0] ]
// 3rd generation: [ [0,1,0], [0,1,0], [0,1,0] ]
//
// Please write a class called board that has at least 
// the following public methods:
// board.init( initial_state [] )
// board.tick() 
// board.print()


package br.com.gameoflife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private static List<List<Integer>> result = new ArrayList<List<Integer>>();
	
	public static void main(String[] args) {
		// data sample used
		//[ [0,1,0], 
		//  [0,1,0], 
		//  [0,1,0] ]
		
		List<List<Integer>> inputData = new ArrayList<List<Integer>>();
		
		List<Integer> inGrid = new ArrayList<Integer>();
		inGrid.add(0);
		inGrid.add(1);
		inGrid.add(0);

		inputData.add(inGrid);
		inputData.add(inGrid);
		inputData.add(inGrid);

		//tests
		result = tick(inputData);
		print();
		
		result = tick(result);
		print();
		
		result = tick(result);
		print();
		
	}
	
	public static void print() {
		if(result.size() == 0) {
			System.out.println("You must run tick");
		}else {
			System.out.println(result);
		}
	}
	
	//business rules 
	public static List<List<Integer>> tick(List<List<Integer>> grid) {
		
		List<List<Integer>> response = new ArrayList<List<Integer>>();
		
		List<List<Neighbors>> outGrid = mapNeighbors(grid);
		
		for (List<Neighbors> list : outGrid) {
			List<Integer> inListReturn = new ArrayList<>();
			
			for (Neighbors cell : list) {
				if(cell.getId().equals(1) && cell.getStatus().get("Live").intValue() < 2) {
					inListReturn.add(0);
					//cell.setId(0);
				}else if(cell.getId().equals(1) && cell.getStatus().get("Live").intValue() > 3) {
					inListReturn.add(0);
					//cell.setId(0);
				}else if(cell.getId().equals(0) && cell.getStatus().get("Live").intValue() == 3) {
					inListReturn.add(1);
					//cell.setId(1);
				}else {
					inListReturn.add(cell.getId());
				}
			}
			
			response.add(inListReturn);
			
//			list.forEach((y) -> {	System.out.print(y.getId().toString() + " -> ");
//									System.out.println(y.getStatus());
//			});
		}
		return response;
	}
	
	//mapping the data to be used
	private static List<List<Neighbors>> mapNeighbors(List<List<Integer>> grid) {

		List<List<Neighbors>> outGrid = new ArrayList<List<Neighbors>>();
		
		for (int firstArray = 0; firstArray < grid.size(); firstArray++) {	
			List<Neighbors> outInteractionGrid = new ArrayList<Neighbors>();
			
			for (int secondArrayPosition = 0; secondArrayPosition < grid.get(firstArray).size(); secondArrayPosition++) {	
				
				Map<String, Integer> status = new HashMap<String, Integer>();
				status.put("Dead", 0);
				status.put("Live", 0);
				
				
				int position = firstArray;
				position --;
				
				int count = 0;
				while (count < 3) {
					
					String statusRes = checkPosition(grid, position, secondArrayPosition -1);
					if(!statusRes.equals("NotAValue")) {
						status.put(statusRes, status.get(statusRes).intValue() + 1);
					}

					if(firstArray != position) {
						statusRes = checkPosition(grid, position, secondArrayPosition);
						
						if(!statusRes.equals("NotAValue")) {
							status.put(statusRes, status.get(statusRes).intValue() + 1);
						}	
					}
					
					statusRes = checkPosition(grid, position, secondArrayPosition + 1);
					if(!statusRes.equals("NotAValue")) {
						status.put(statusRes, status.get(statusRes).intValue() + 1);
					}
								
					position ++;
					count ++;
				}
				
				outInteractionGrid.add(new Neighbors(grid.get(firstArray).get(secondArrayPosition), status));
			}
			outGrid.add(outInteractionGrid);
		}
		return outGrid;
	}
	
	
	private static String checkPosition(List<List<Integer>> inList, int indexFirstArray, int indexCell) {
		
		try {
			if(inList.get(indexFirstArray).get(indexCell).equals(0)) {
				return "Dead";
			}
			return "Live";
		} catch ( IndexOutOfBoundsException e ) {
		    return "NotAValue";
		}
	}
}

//return encapsulation 
class Neighbors {
	private Integer id;
	private Map<String, Integer> status;
	
	Neighbors(Integer id, Map<String, Integer> status){
		this.id = id;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Map<String, Integer> getStatus() {
		return status;
	}
	public void setStatus(Map<String, Integer> status) {
		this.status = status;
	}
}
