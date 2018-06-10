package goeuro.task;

import java.util.List;

interface DirectConnectionService {
	
	boolean existDirectConnection(int dep_sid, int arr_sid);
	List<int[]> getRoutsList();

}
