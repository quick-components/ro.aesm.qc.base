package ro.aesm.qc.base;

import ro.aesm.qc.api.base.IExecutionContext;

public class ExecutionContext implements IExecutionContext {

	private String id;
	private String user;

	public ExecutionContext(String id, String user) {
		super();
		this.id = id;
		this.user = user;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getUser() {
		return user;
	}

}
