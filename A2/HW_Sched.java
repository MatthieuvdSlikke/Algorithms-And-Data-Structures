package A2;
import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * The way you implement this method will define which order the assignments appear in when you sort.
	 * Return 1 if a1 should appear after a2
	 * Return -1 if a1 should appear before a2
	 * Return 0 if a1 and a2 are equivalent 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {

		if(a1.deadline < a2.deadline){		        
			return -1;}  // a1 before a2
		
		else if (a1.deadline > a2.deadline){	 //due date is more important	
			return 1;} // a1 after a2
		
		else{		     						
			
			if (a1.weight > a2.weight){       	// if due date is the same check weights
				return -1;} 	// a1 before a2				
		
			else if(a1.weight < a2.weight){  
				return 1;} // a1 after a2
			
			else{return 0;}						// all equal
		}
	}	
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
	 * The homework you complete first will be given an output of 1, the second, 2, etc.
	 */
	public int[] SelectAssignments() {
		//Use the following command to sort your Assignments: 
		//Collections.sort(Assignments, new Assignment());
		//This will re-order your assignments. The resulting order will depend on how the compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		//Initializes the homeworkPlan, which you must fill out and output
		int[] homeworkPlan = new int[Assignments.size()];
		
		int hw = 0;						
		
		for(int i = 0 ; i < Assignments.size() ; i++){ // loop over every assignments
			
			Assignment a1 = Assignments.get(i);	
			if(i == 0){ 
				hw++; // put the first assignment to Homework plan
				homeworkPlan[a1.number]= hw;
				continue;}

			Assignment a2;
			if(i < Assignments.size() - 1) {
				a2= Assignments.get(i+1 );} // get the next assignment if possible
			
			
			else{ 	if( a1.deadline != hw ){   //if the deadline of all assignments is achieved return homework plan
					hw++;
						homeworkPlan[a1.number]= hw; }
					else{         
						return homeworkPlan;}
					return homeworkPlan; }

			if( a1.deadline != a2.deadline ) {  // if deadline is different      
				hw++;
				homeworkPlan[a2.number] = hw; }
			else{ 
				if(a2.deadline == hw) {     //case where deadline = hw
					homeworkPlan[a2.number]= 0;}
				else {    
					hw++;
				homeworkPlan[a1.number] =hw;} }
			}
		
		return homeworkPlan;
	}
}
	



