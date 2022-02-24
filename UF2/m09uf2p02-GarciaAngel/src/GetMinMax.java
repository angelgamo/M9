import java.util.List;
import java.util.concurrent.Callable;

public class GetMinMax implements Callable<Result>{
	List<Integer> list = null;
	int a;
	Result result = new Result();
	
	public GetMinMax(List<Integer> list) {
		super();
		this.list = list;
	}
	
	@Override
	public Result call() throws Exception {
		for (int i = 0; i < list.size(); i++) {
			int a = list.get(i);
			if (a < result.min) result.min = a;
			if (a > result.max) result.max = a;
		}
		return result;
	}
}
