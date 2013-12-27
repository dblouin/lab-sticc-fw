package fr.labsticc.framework.core.exception;

public class CyclicDependencyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1820103239317810471L;
	
	private final Object dependency1;

	private final Object dependency2;

	public CyclicDependencyRuntimeException( 	final Object p_dependency1,
												final Object p_dependency2 ) {
		dependency1 = p_dependency1;
		dependency2 = p_dependency2;
	}

	public Object getDependency1() {
		return dependency1;
	}

	public Object getDependency2() {
		return dependency2;
	}
}
