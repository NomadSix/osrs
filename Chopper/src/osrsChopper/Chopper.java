package osrsChopper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt4.ClientContext;


@Manifest(name = "chop'n'drop", description = "cuts logs and drops them")
public class Chopper extends PollingScript<ClientContext>{
	private List<Task<?>> taskList = new ArrayList<Task<?>>();
	
	@Override
	public void start() {
		taskList.addAll(Arrays.asList(new Chop(ctx), new Drop(ctx)));
	}
	
	@Override
	public void poll() {
		for (Task<?> task : taskList) {
			if (task.activate()) {
				task.execute();
			}
		}
	}

}
