package snail.ognltest;

public class CommandImpl implements Command {
	
	private Receiver rec;

	@Override
	public void doCommand() {
		rec.print();
	}

}
