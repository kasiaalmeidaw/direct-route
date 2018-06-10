package goeuro.task;

import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * 
 * Service manage information about connections. Keep conection info in data
 * structure that minimalize usage of memory
 *
 */
@Service
public class DirectConnectionServiceImpl implements DirectConnectionService {

	private List<int[]> routsList = new LinkedList<int[]>();

	@Override
	public boolean existDirectConnection(int dep_sid, int arr_sid) {
		return routsList.stream().anyMatch(a -> {
			Set<Integer> route = Arrays.stream(a).boxed().collect(Collectors.<Integer>toSet());
			return route.contains(dep_sid) && route.contains(arr_sid);
		});
	}

	public List<int[]> getRoutsList() {
		return routsList;
	}

}
