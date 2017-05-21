package bases;

public interface Cell extends Component {

	boolean isMarked();
	
	boolean isRevealed();
	
	void mark();
	
	void unmark();
	
	void reveal();
}
