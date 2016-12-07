import java.util.List;

public interface BoundingBoxInterface {
	
	public List<String> fetchBoundingTriples(int leftXInt, int rightXInt, int lowerYInt, int upperYInt);
	
}
