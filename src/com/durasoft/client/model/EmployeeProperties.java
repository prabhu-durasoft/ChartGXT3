package com.durasoft.client.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface EmployeeProperties extends PropertyAccess<Employee>{
	ValueProvider<Employee, String> name();
	ValueProvider<Employee, Integer> yearsOfExperience();
 	@Path("name")
	ModelKeyProvider<Employee> nameKey();
}
