/*
Copyright (c) 2011, Rockwell Collins.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/

package com.rockwellcollins.atc.meta.lute;

import org.osate.aadl2.NamedElement;

public class AADLVal extends Val {
	final private NamedElement aadlElement;
	
	public AADLVal( final NamedElement p_aadlElement ) {
		super();
		
		aadlElement = p_aadlElement;
	}

	@Override
	public NamedElement getAADL() {
		return aadlElement;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AADLVal) {
			final AADLVal other = (AADLVal) obj;
			
			return getAADL().equals(other.getAADL());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return aadlElement.hashCode();
	}
	
	@Override
	public String toString() {
		// Full name of the aadl object excluding the final system instance name
		final StringBuilder result = new StringBuilder();
		result.append( aadlElement.getName() );
		NamedElement curr = aadlElement;
		
		while (curr.getOwner() instanceof NamedElement && curr.getOwner().getOwner() != null) {
			curr = (NamedElement) curr.getOwner();
			result.insert(0, ".");
			result.insert(0, curr.getName());
		}
		
		return result.toString();
	}
}
